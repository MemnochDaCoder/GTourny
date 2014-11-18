package edu.utah.cs4962.battleshiptest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameFunctions
{
    ArrayList<Integer> _player1Ships;
    ArrayList<Integer> _player2Ships;
    ArrayList<Integer> _tempShipPlacement;
    public ArrayList<Cell> _player1Board, _player2Board, _player1Opponent, _player2Opponent;
    boolean _gameOver;
    boolean _p1WINNER, _p2WINNER;
    int _p1HITS, _p2HITS;
    private static boolean _isPlayer1 = false;

    /*
    Constructor method that sets up the game boards.
     */
    public GameFunctions()
    {
        _gameOver = false;
        _p1WINNER = false;
        _p2WINNER = false;
        _p1HITS = 0;
        _p2HITS = 0;
        _player1Ships = new ArrayList<Integer>();
        _player2Ships = new ArrayList<Integer>();
        _isPlayer1 = true;

        placeShip(_player1Ships, 5);
        placeShip(_player1Ships, 4);
        placeShip(_player1Ships, 3);
        placeShip(_player1Ships, 3);
        placeShip(_player1Ships, 2);
        placeShip(_player2Ships, 5);
        placeShip(_player2Ships, 4);
        placeShip(_player2Ships, 3);
        placeShip(_player2Ships, 3);
        placeShip(_player2Ships, 2);

        _player1Board = new ArrayList<Cell>();
        _player2Board = new ArrayList<Cell>();
        _player1Opponent = new ArrayList<Cell>();
        _player2Opponent = new ArrayList<Cell>();

        setPlayerBoards();
    }

    /**
     * Set's up the cell arrays that represent the four player states.
     */
    public void setPlayerBoards()
    {
        for (int i = 0; i < 100; i++)
        {
            if (_player1Ships.contains(i))
            {
                _player1Board.add(new Cell(2));
            }
            else
                _player1Board.add(new Cell(3));
        }

        for (int i = 0; i < 100; i++)
        {
            if (_player2Ships.contains(i))
            {
                _player2Board.add(new Cell(2));
            }
            else
                _player2Board.add(new Cell(3));
        }

        for (int i = 0; i< 100; i++)
        {
            _player1Opponent.add(new Cell(3));
            _player2Opponent.add(new Cell(3));
        }
    }

    /**
     * Determines if player 1 has hit a player 2 ship and returns true if it's a
     * hit false if it's anything else.
     *
     * @param spot
     * @return boolean
     */
    public boolean player1ShootMissile(int spot)
    {
        switch(_player2Board.get(spot).getState())
        {
            case 0:
                this._isPlayer1 = !this._isPlayer1;
                return false;
            case 1:
                this._isPlayer1 = !this._isPlayer1;
                return true;
            case 2:
                _player2Board.set(spot, new Cell(1));
                _player1Opponent.set(spot, new Cell(1));
                _p2HITS++;
                this._isPlayer1 = !this._isPlayer1;
                return true;
            default:
                _player2Board.set(spot, new Cell(0));
                _player1Opponent.set(spot, new Cell(0));
                this._isPlayer1 = !this._isPlayer1;
                return false;
        }
    }

    /**
     * Determines if player 1 has hit a player 2 ship and returns true if it's a
     * hit false if it's anything else.
     *
     * @param spot
     * @return
     */
    public boolean player2ShootMissile(int spot)
    {
        switch(_player1Board.get(spot).getState())
        {
            case 0:
                this._isPlayer1 = !this._isPlayer1;
                return false;
            case 1:
                this._isPlayer1 = !this._isPlayer1;
                return true;
            case 2:
                _player1Board.set(spot, new Cell(1));
                _player2Opponent.set(spot, new Cell(1));
                _p2HITS++;
                this._isPlayer1 = !this._isPlayer1;
                return true;
            default:
                _player1Board.set(spot, new Cell(0));
                _player2Opponent.set(spot, new Cell(0));
                this._isPlayer1 = !this._isPlayer1;
                return false;
        }
    }

    /**
     * Returns true if the current player is player 1.
     *
     * @return
     */
    public boolean isPlayer1()
    {
        return _isPlayer1;
    }

    /**
     * Sets the global _isPlayer1 boolean.
     *
     * @param _isPlayer1
     */
    public static void set_isPlayer1(boolean _isPlayer1)
    {
        GameFunctions._isPlayer1 = _isPlayer1;
    }

    /**
        Check if there is a winner by counting the number of hits.

        @return boolean
     **/
    public boolean checkGame()
    {
        int player1Hits = 0;
        int player2Hits = 0;

        for(Cell cell : _player1Board)
        {
            if(cell.getState() == 1)
                player1Hits++;
        }

        _p1HITS = player1Hits;

        for(Cell cell : _player2Board)
        {
            if(cell.getState() == 1)
                player2Hits++;
        }

        _p2HITS = player2Hits;

        if (player1Hits == 17)
        {
            _p2WINNER = true;
            _gameOver = true;
            return true;
        }
        if (player2Hits == 17)
        {
            _p1WINNER = true;
            _gameOver = true;
            return true;
        }

        return false;
    }

    /**
     * Gets a random number 0-99
     *
     * @return int random
     */
    public int randomPlace()
    {
        Random rand = new Random();
        int random = rand.nextInt(99 - 0 + 1) + 0;

        return random;
    }

    /**
     * Tests and rotates ship if required, then sets ship
     *
     * @param playerShips
     * @param shipSize
     * @return boolean
     */
    public boolean setShip(ArrayList<Integer> playerShips, int shipSize)
    {
        int spot = randomPlace();
        boolean vertical = Math.random() < 0.5;

        if (vertical)
        {
            if (setShipVert(spot, shipSize, playerShips))
                return true;
            rotateShip(vertical);
            if (setShipHoriz(spot, shipSize, playerShips))
                return true;
            else
                return false;
        }

        if (!vertical)
        {
            if (setShipHoriz(spot, shipSize, playerShips))
                return true;
            rotateShip(vertical);
            if (setShipVert(spot, shipSize, playerShips))
                return true;
            else
                return false;
        }

        return false;
    }

    /**
     * Tries to set ship in a vertical position facing up or down.
     *
     * @param spot
     * @param shipSize
     * @param playerShips
     * @return boolean
     */
    public boolean setShipVert(int spot, int shipSize,
                               ArrayList<Integer> playerShips)
    {
        if (checkUP(spot, shipSize, playerShips))
        {
            return true;
        }
        if (checkDOWN(spot, shipSize, playerShips))
        {
            return true;
        }

        return false;
    }

    /**
     * Tries to set ship in a horizontal position facing left or right.
     *
     * @param spot
     * @param shipSize
     * @param playerShips
     * @return boolean
     */
    public boolean setShipHoriz(int spot, int shipSize,
                                ArrayList<Integer> playerShips)
    {
        if (checkLEFT(spot, shipSize, playerShips))
        {
            return true;
        }
        if (checkRIGHT(spot, shipSize, playerShips))
        {
            return true;
        }
        return false;
    }

    /**
     * Loops until ship is placed in board.
     *
     * @param playerShips
     * @param shipSize
     * @return boolean
     */
    public boolean placeShip(ArrayList<Integer> playerShips, int shipSize)
    {
        boolean loop = false;
        do
        {
            if (setShip(playerShips, shipSize))
            {
                for (int spotToAdd : _tempShipPlacement)
                    playerShips.add(spotToAdd);
                loop = true;
            }
        } while (!loop);

        Collections.sort(playerShips);

        return true;
    }

    /**
     * Rotates ship by changing boolean value.
     *
     * @param vertical
     * @return boolean
     */
    public boolean rotateShip(boolean vertical)
    {
        if (vertical)
            vertical = false;
        if (!vertical)
            vertical = true;
        return vertical;
    }

    /**
     * Tests player array list / board to see if random spot is contained.
     *
     * @param playerShips
     * @param spot
     * @return boolean
     */
    public boolean isSpotEmpty(ArrayList<Integer> playerShips, int spot)
    {
        if (playerShips.contains(spot))
            return false;
        return true;
    }

    /**
     * Checks if ship can be placed in upward direction and sets array.
     *
     * @param spot
     * @param shipSize
     * @param playerShips
     * @return boolean
     */
    public boolean checkUP(int spot, int shipSize,
                           ArrayList<Integer> playerShips)
    {
        _tempShipPlacement = new ArrayList<Integer>();

        if (!checkVerticalBoundsUP(spot, shipSize))
            return false;

        for (int i = 0; i < shipSize; i++)
        {
            if (isSpotEmpty(playerShips, spot))
            {
                _tempShipPlacement.add(spot);
                spot = spot - 10;
            } else
            {
                _tempShipPlacement.clear();
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if upper bound is crossed by ship.
     *
     * @param spot
     * @param shipSize
     * @return boolean
     */
    public boolean checkVerticalBoundsUP(int spot, int shipSize)
    {
        // Vertical going up
        int test = spot - ((shipSize - 1) * 10);
        if (test > 0)
        {
            return true;
        }

        return false;
    }

    /**
     * Checks if ship can be placed in downward direction and sets array.
     *
     * @param spot
     * @param shipSize
     * @param playerShips
     * @return boolean
     */
    public boolean checkDOWN(int spot, int shipSize,
                             ArrayList<Integer> playerShips)
    {
        _tempShipPlacement = new ArrayList<Integer>();

        if (!checkVerticalBoundsDOWN(spot, shipSize))
            return false;

        for (int i = 0; i < shipSize; i++)
        {
            if (isSpotEmpty(playerShips, spot))
            {
                _tempShipPlacement.add(spot);
                spot = spot + 10;
            } else
            {
                _tempShipPlacement.clear();
                return false;
            }
        }
        return true;
    }

    /**
     * Checks of bottom bounds are crossed by ship.
     *
     * @param spot
     * @param shipSize
     * @return boolean
     */
    public boolean checkVerticalBoundsDOWN(int spot, int shipSize)
    {
        // Vertical going down
        int test = spot + ((shipSize - 1) * 10);
        if (test < 100)
        {
            return true;
        }

        return false;
    }

    /**
     * Checks if ship can be placed in right direction and sets array.
     *
     * @param spot
     * @param shipSize
     * @param playerShips
     * @return boolean
     */
    public boolean checkRIGHT(int spot, int shipSize,
                              ArrayList<Integer> playerShips)
    {
        _tempShipPlacement = new ArrayList<Integer>();

        if (!checkHorizontalBoundsRIGHT(spot, shipSize))
            return false;

        for (int i = 0; i < shipSize; i++)
        {
            if (isSpotEmpty(playerShips, spot))
            {
                _tempShipPlacement.add(spot);
                spot = spot + 1;
            } else
            {
                _tempShipPlacement.clear();
                return false;
            }
        }
        return true;
    }

    /**
     * Checks of right bounds are crossed by ship.
     *
     * @param spot
     * @param shipSize
     * @return
     */
    public boolean checkHorizontalBoundsRIGHT(int spot, int shipSize)
    {
        // Horizontal going right
        int test = ((spot % 10) + shipSize) - 1;
        if (test < 10)
        {
            return true;
        }

        return false;
    }

    /**
     * Checks if ship can be placed in left direction and sets array.
     *
     * @param spot
     * @param shipSize
     * @param playerShips
     * @return boolean
     */
    public boolean checkLEFT(int spot, int shipSize,
                             ArrayList<Integer> playerShips)
    {
        _tempShipPlacement = new ArrayList<Integer>();

        if (!checkHorizontalBoundsLEFT(spot, shipSize))
            return false;

        for (int i = 0; i < shipSize; i++)
        {
            if (isSpotEmpty(playerShips, spot))
            {
                _tempShipPlacement.add(spot);
                spot = spot - 1;
            } else
            {
                _tempShipPlacement.clear();
                return false;
            }
        }
        return true;
    }

    /**
     * Checks of left bounds are crossed by ship.
     *
     * @param spot
     * @param shipSize
     * @return
     */
    public boolean checkHorizontalBoundsLEFT(int spot, int shipSize)
    {
        // Horizontal going left
        int test = ((spot % 10) - shipSize) + 1;
        if (test > 0)
        {
            return true;
        }

        return false;
    }

}
