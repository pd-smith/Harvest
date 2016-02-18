package com.harvest.effects;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.harvest.game.GameDriver;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/**
 * Created by Patty on 2/17/16.
 */
public class Transition {

    GameDriver game;
    public Transition(GameDriver game){
        this.game = game;
    }
    public SequenceAction toBattle(Screen overworld){
        System.out.println("Transition");
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(new RunnableAction(){
            @Override
            public void run(){
                game.setBattle();
            }
        });
        return sequenceAction;
    }
}
