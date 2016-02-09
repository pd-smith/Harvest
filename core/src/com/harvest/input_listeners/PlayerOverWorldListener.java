package com.harvest.input_listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.harvest.actors.PlayerOverWorld;
import com.harvest.hud_elements.OverWorldHUD;

/**
 * Created by Patty on 1/24/2016.
 */
public class PlayerOverWorldListener implements InputProcessor, EventListener{

    PlayerOverWorld _player;
    OverWorldHUD _hud;
    int movementCounter;
    boolean movingWest,movingNorth,movingEast,movingSouth;

    public PlayerOverWorldListener(PlayerOverWorld player, OverWorldHUD hud){
        _player = player;
        _hud = hud;
        movementCounter = 0;
        Gdx.input.setInputProcessor(this);
        movingWest = false;
        movingNorth = false;
        movingEast = false;
        movingSouth = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.LEFT:
            case Input.Keys.A:
                if(!_hud.getStatsCard().canShow()) {
                    if (!_hud.getPauseCard().canShow()){
                        _player.moveWest();
                        movingWest = true;
                        movingEast = false;
                        movingSouth = false;
                        movingNorth = false;
                    }
                }else{
                    _player.getPlayerState().getInventory().indexPrevious();
                }
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                if(!_hud.getStatsCard().canShow()) {
                    if (!_hud.getPauseCard().canShow()) {
                        _player.moveEast();
                        movingEast = true;
                        movingWest = false;
                        movingSouth = false;
                        movingNorth = false;
                    }
                }else{
                    _player.getPlayerState().getInventory().indexNext();
                }
                break;
            case Input.Keys.UP:
            case Input.Keys.W:
                if(!_hud.getStatsCard().canShow()) {
                    if (!_hud.getPauseCard().canShow()) {
                        _player.moveNorth();
                        movingNorth = true;
                        movingWest = false;
                        movingSouth = false;
                        movingEast = false;
                    }else{
                        _hud.getPauseCard().moveOptionsUp();
                    }
                }else{
                    _player.getPlayerState().getInventory().indexJumpUp();
                }
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                if(!_hud.getStatsCard().canShow()) {
                    if (!_hud.getPauseCard().canShow()) {
                        _player.moveSouth();
                        movingSouth = true;
                        movingWest = false;
                        movingEast = false;
                        movingNorth = false;
                    }else{
                        _hud.getPauseCard().moveOptionDown();
                    }
                }else{
                    _player.getPlayerState().getInventory().indexJumpDown();
                }
                break;
            case Input.Keys.ENTER:
                if(!_hud.getPauseCard().canShow() && !_hud.getStatsCard().canShow()) {
                    _hud.getStatsCard().showStats();
                }
                if(_hud.getPauseCard().canShow()){
                    _hud.getPauseCard().onSelect();
                }
                break;
            case Input.Keys.ESCAPE:
                if(_hud.getStatsCard().canShow()) {
                    _hud.getStatsCard().hideStats();
                }else if(!_hud.getPauseCard().canShow()){
                    _hud.getPauseCard().showPause();
                }else{
                    _hud.getPauseCard().hidePause();
                }


                break;

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.LEFT:
            case Input.Keys.A:
                movingWest = false;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                movingEast = false;
                break;
            case Input.Keys.UP:
            case Input.Keys.W:
                movingNorth = false;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                movingSouth = false;
                break;
        }
        if(hasMovementStopped()){
            _player.stopMovement();
        }
        return false;
    }

    public boolean hasMovementStopped(){
        return !(movingSouth || movingNorth || movingEast || movingWest);
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
