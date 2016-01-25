package com.harvest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.harvest.input_listeners.PlayerOverWorldListener;
import com.harvest.scenes.SceneOverWorld;

/**
 * Created by Patty on 1/23/2016.
 */
public class PlayerOverWorld extends Actor {
    private static final int FRAME_COLS = 12;
    private static final int FRAME_ROWS = 8;
    private static final int FRAME_LENGTH = 3;
    private static final float ANIM_SPEED = .30f;
    private static final float WALK_AMOUNT = 3f;


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


    boolean isMoving;
    float stateTime;

    public PlayerOverWorld(){
        isMoving = false;

        setUpAnimations();

        setBounds(0,0,currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        addListener((EventListener) new PlayerOverWorldListener(this));

    }

    public PlayerOverWorld(SceneOverWorld scene){
        isMoving = false;

        setUpAnimations();

        setBounds(0,0,currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        addListener((EventListener) new PlayerOverWorldListener(this));
        body = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
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
        sheet = new Texture(Gdx.files.internal("testsheet.png"));
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth()/FRAME_COLS, sheet.getHeight()/FRAME_ROWS);
        walkingNorth = new TextureRegion[FRAME_LENGTH];
        walkingNorth[0] = tmp[0][0];
        walkingNorth[1] = tmp[0][1];
        walkingNorth[2] = tmp[0][2];
        walkingSouth = new TextureRegion[FRAME_LENGTH];
        walkingSouth[0] = tmp[2][0];
        walkingSouth[1] = tmp[2][1];
        walkingSouth[2] = tmp[2][2];
        walkingWest = new TextureRegion[FRAME_LENGTH];
        walkingWest[0] = tmp[3][0];
        walkingWest[1] = tmp[3][1];
        walkingWest[2] = tmp[3][2];
        walkingEast = new TextureRegion[FRAME_LENGTH];
        walkingEast[0] = tmp[1][0];
        walkingEast[1] = tmp[1][1];
        walkingEast[2] = tmp[1][2];



        animNorth = new Animation(ANIM_SPEED, walkingNorth);
        animNorth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animSouth = new Animation(ANIM_SPEED, walkingSouth);
        animSouth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animWest = new Animation(ANIM_SPEED, walkingWest);
        animWest.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animEast = new Animation(ANIM_SPEED, walkingEast);
        animEast.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        stateTime = 0f;
        animCurr = animSouth;

        currentFrame = animCurr.getKeyFrame(1);
    }

    private boolean addActionAsNeeded(){
        /*if(animCurr.getKeyFrameIndex(stateTime)%2 != 0){
            return false;
        }*/
        switch (getDirection()){
            case 0:
                this.addAction(Actions.moveTo(this.getX(),this.getY() + WALK_AMOUNT,ANIM_SPEED/2));
                break;
            case 1:
                this.addAction(Actions.moveTo(this.getX()+ WALK_AMOUNT,this.getY(),ANIM_SPEED/2));
                break;
            case 2:
                this.addAction(Actions.moveTo(this.getX(),this.getY() - WALK_AMOUNT,ANIM_SPEED/2));
                break;
            case 3:
                this.addAction(Actions.moveTo(this.getX() - WALK_AMOUNT,this.getY(),ANIM_SPEED/2));
                break;
            default:
                System.err.println("Failed to add action. Direction not Recognized!");
                break;
        }
        return true;
    }

}
