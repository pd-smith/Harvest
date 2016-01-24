package com.harvest.input_listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.harvest.actors.PlayerOverWorld;

/**
 * Created by Patty on 1/24/2016.
 */
public class PlayerOverWorldListener implements InputProcessor, EventListener{

    PlayerOverWorld _player;
    int movementCounter;

    public PlayerOverWorldListener(PlayerOverWorld player){
        _player = player;
        movementCounter = 0;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.A:
                movementCounter ++;
                _player.moveWest();
                break;
            case Input.Keys.D:
                movementCounter ++;
                _player.moveEast();
                break;
            case Input.Keys.W:
                movementCounter ++;
                _player.moveNorth();
                break;
            case Input.Keys.S:
                movementCounter ++;
                _player.moveSouth();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        //have to have a way to check if last key is still down and switch to that movement
        switch(keycode){
            case Input.Keys.A:
                movementCounter --;
                break;
            case Input.Keys.D:
                movementCounter --;
                break;
            case Input.Keys.W:
                movementCounter --;
                break;
            case Input.Keys.S:
                movementCounter --;
                break;
        }
        if(movementCounter == 0){
            _player.stopMovement();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean handle(Event event) {
        return false;
    }
}
