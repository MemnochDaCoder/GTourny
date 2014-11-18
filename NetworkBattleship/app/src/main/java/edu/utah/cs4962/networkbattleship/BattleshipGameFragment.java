package edu.utah.cs4962.networkbattleship;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class BattleshipGameFragment extends Fragment implements GameViewGroup.OnCellClickedListener
{
    GameViewGroup _gameBoard = null;
    public boolean _isInteractable = false;

    OnTurnFinishedListener _onTurnFinishedListener = null;

    /**
     * Sets the interactivity of the view.
     *
     * @param isInteractable
     */
    public void setInteractable(boolean isInteractable)
    {
        _isInteractable = isInteractable;
    }

    /**
     * Sets the cells for the view in the game.
     *
     * @param cells
     */
    public void setCells(final ArrayList<Cell> cells)
    {
        _gameBoard.post(new Runnable()
        {
            @Override
            public void run()
            {
                _gameBoard.setCells(cells);
            }
        });
    }

    /**
     * Interface for the finished turn.
     */
    public interface OnTurnFinishedListener
    {
        void onTurnFinished();
    }

    /**
     * On turn finished listener sets the global value on click.
     *
     * @param onTurnFinishedListener
     */
    public void setOnTurnFinishedListener(OnTurnFinishedListener onTurnFinishedListener)
    {
        _onTurnFinishedListener = onTurnFinishedListener;
    }

    /**
     * On Create View sets the initial view for the game.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        _gameBoard = new GameViewGroup(getActivity(), new ArrayList<Cell>());
        _gameBoard.setBackgroundResource(R.drawable.ocean);

        _gameBoard.setOnCellClickedListener(this);

        return _gameBoard;
    }

    /**
     * Using the index for the set cell will check the model for a hit depending
     * on who the active player is according to the model and change the cell's
     * background for a hit or a miss.
     *
     * @param cellIndex
     */
    @Override
    public void OnCellClick(int cellIndex)
    {
        Game game = Game.getSetInstance();
        _isInteractable = false;

        if (game._gameFunctions.isPlayer1())
        {
            if (game._gameFunctions.player1ShootMissile(cellIndex))
                _gameBoard.setCellState(cellIndex, Color.RED);
            else
                _gameBoard.setCellState(cellIndex, Color.WHITE);

        }
        else
        {
            if (game._gameFunctions.player2ShootMissile(cellIndex))
                _gameBoard.setCellState(cellIndex, Color.RED);
            else
                _gameBoard.setCellState(cellIndex, Color.WHITE);
        }

        new CountDownTimer(0, 1000)
        {

            public void onTick(long millisUntilFinished)
            {
            }

            public void onFinish()
            {
                _onTurnFinishedListener.onTurnFinished();
            }
        }.start();
    }
}
