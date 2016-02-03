package com.harvest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.harvest.environment.overworld.Map;
import com.harvest.hud_elements.HUDVars;
import com.harvest.hud_elements.OverWorldHUD;
import com.harvest.input_listeners.PlayerOverWorldListener;
import com.harvest.scenes.SceneOverWorld;

/**
 * Created by Patty on 1/23/2016.
 */
public class PlayerOverWorld extends Actor {
    private final float WALK_AMOUNT;

    Animation animNorth;
    Animation animSouth;
    Animation animWest;
    Animation animEast;
    Animation animCurr;

    Texture sheet;
    TextureRegion[] walkingNorth;
    TextureRegion[] walkingSouth;
    TextureRegion[] walkingWest;
    TextureRegion[] walkingEast;

    Rectangle body;

    TextureRegion currentFrame;

    Map map;
    boolean isMoving;
    float stateTime;

    PlayerState playerState;


    public PlayerOverWorld(SceneOverWorld scene, Map currentMap, OverWorldHUD hud){
        isMoving = false;
        setUpAnimations();
        map = currentMap;
        WALK_AMOUNT = map.getCollisionLayer().getTileWidth()/2;

        setBounds(0,0,16,currentFrame.getRegionHeight());
        System.out.println(currentFrame.getRegionWidth() + " " + currentFrame.getRegionHeight());
        addListener(new PlayerOverWorldListener(this,hud));
        body = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());

        playerState = new PlayerState("Someone");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isMoving){
            addActionAsNeeded();
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = animCurr.getKeyFrame(stateTime, true);
        }
        batch.draw(currentFrame, this.getX(), this.getY());

    }

    @Override
    public Actor hit(float arg0, float arg1, boolean flag) {
        return super.hit(arg0, arg1, flag);
    }

    public void moveNorth(){
        isMoving = true;
        animCurr = animNorth;
    }

    public void moveSouth(){
        isMoving = true;
        animCurr = animSouth;
    }

    public void moveWest(){
        isMoving = true;
        animCurr = animWest;
    }

    public void moveEast(){
        isMoving = true;
        animCurr = animEast;
    }

    public void stopMovement(){
        isMoving = false;
        switch(getDirection()){
            case 0:
                currentFrame = animNorth.getKeyFrame(1);
                break;
            case 1:
                currentFrame = animEast.getKeyFrame(1);
                break;
            case 2:
                currentFrame = animSouth.getKeyFrame(1);
                break;
            case 3:
                currentFrame = animWest.getKeyFrame(1);
                break;
            default:
                System.err.print("Couldn't find correct animation");
                break;
        }
        stateTime = 0;
    }

    public int getDirection(){
        if(animCurr == animNorth){
            return 0;
        }else if(animCurr == animSouth){
            return 2;
        }else if(animCurr == animWest){
            return 3;
        }else if(animCurr == animEast){
            return 1;
        }else{
            return -1;
        }
    }

    private void setUpAnimations(){

        //Perhaps we should make this modular? Or maybe more flexable for custom sprites

        sheet = new Texture(Gdx.files.internal("Characters/testsheet.png"));
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth()/PlayerVars.FRAME_COLS, sheet.getHeight()/PlayerVars.FRAME_ROWS);

        walkingNorth = new TextureRegion[PlayerVars.FRAME_LENGTH];
        walkingNorth[0] = tmp[0][0];
        walkingNorth[1] = tmp[0][1];
        walkingNorth[2] = tmp[0][2];
        walkingSouth = new TextureRegion[PlayerVars.FRAME_LENGTH];
        walkingSouth[0] = tmp[2][0];
        walkingSouth[1] = tmp[2][1];
        walkingSouth[2] = tmp[2][2];
        walkingWest = new TextureRegion[PlayerVars.FRAME_LENGTH];
        walkingWest[0] = tmp[3][0];
        walkingWest[1] = tmp[3][1];
        walkingWest[2] = tmp[3][2];
        walkingEast = new TextureRegion[PlayerVars.FRAME_LENGTH];
        walkingEast[0] = tmp[1][0];
        walkingEast[1] = tmp[1][1];
        walkingEast[2] = tmp[1][2];



        animNorth = new Animation(PlayerVars.ANIM_SPEED, walkingNorth);
        animNorth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animSouth = new Animation(PlayerVars.ANIM_SPEED, walkingSouth);
        animSouth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animWest = new Animation(PlayerVars.ANIM_SPEED, walkingWest);
        animWest.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animEast = new Animation(PlayerVars.ANIM_SPEED, walkingEast);
        animEast.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        stateTime = 0f;
        animCurr = animSouth;

        currentFrame = animCurr.getKeyFrame(1);
    }

    private boolean addActionAsNeeded(){

        float walkSpeed = PlayerVars.ANIM_SPEED/2;
        switch (getDirection()){
            case 0:
                if(!isBlockedNorth())
                    this.addAction(Actions.moveTo(this.getX(),this.getY() + WALK_AMOUNT,walkSpeed));
                break;
            case 1:
                if(!isBlockedEast())
                    this.addAction(Actions.moveTo(this.getX()+ WALK_AMOUNT,this.getY(),walkSpeed));
                break;
            case 2:
                if(!isBlockedSouth())
                    this.addAction(Actions.moveTo(this.getX(),this.getY() - WALK_AMOUNT,walkSpeed));
                break;
            case 3:
                if(!isBlockedWest())
                    this.addAction(Actions.moveTo(this.getX() - WALK_AMOUNT,this.getY(),walkSpeed));
                break;
            default:
                System.err.println("Failed to add action. Direction not Recognized!");
                break;
        }
        return true;
    }



    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer collisionLayer = map.getCollisionLayer();
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null;
    }

    public boolean isBlockedNorth(){
        float increment = WALK_AMOUNT;
        //increment = getHeight() < increment ? getHeight() / 2 : increment / 2;
        for(float step = 0; step <= getWidth()-getWidth()/4; step += increment)
            if(isCellBlocked(getX() + step, getY() + getWidth()))
                return true;
        return false;
    }

    public boolean isBlockedSouth(){
        float increment = WALK_AMOUNT;
        //increment = getWidth() < increment ? getWidth() / 2 : increment / 2;
        for(float step = 0; step <= getWidth()-getWidth()/4; step += increment)
            if(isCellBlocked(getX() + step, getY()))
                return true;
        return false;
    }

    public boolean isBlockedWest(){
        float increment = WALK_AMOUNT;
        //increment = getWidth() < increment ? getWidth() / 2 : increment / 2;
        for(float step = 0; step <= getHeight()/2; step += increment)
            if(isCellBlocked(getX(), getY() + step))
                return true;
        return false;
    }

    public boolean isBlockedEast(){
        float increment = WALK_AMOUNT;
        //increment = getWidth() < increment ? getWidth() / 2 : increment / 2;
        for(float step = 0; step <= getHeight()/2; step += increment)
            if(isCellBlocked(getX() + getWidth(), getY() + step))
                return true;
        return false;
    }

    public PlayerState getPlayerState(){
        return playerState;
    }

}
