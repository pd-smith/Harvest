package com.harvest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Created by Patty on 1/23/2016.
 */
public class PlayerOverWorld extends Actor {
    private static final int FRAME_COLS = 12;
    private static final int FRAME_ROWS = 8;

    private static final int FRAME_LENGTH = 3;

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

    SpriteBatch batch;
    TextureRegion currentFrame;

    float stateTime;

    public PlayerOverWorld(){


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



        animNorth = new Animation(.4f, walkingNorth);
        animNorth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animSouth = new Animation(.4f, walkingSouth);
        animSouth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animWest = new Animation(.4f, walkingWest);
        animWest.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animEast = new Animation(.4f, walkingEast);
        animEast.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        stateTime = 0f;
        animCurr = animSouth;

        currentFrame = animCurr.getKeyFrame(stateTime, true);

        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch(keycode){
                    case Input.Keys.A:
                        System.out.println(true);
                        animCurr = animWest;
                        break;
                    case Input.Keys.D:
                        animCurr = animEast;
                        break;
                    case Input.Keys.W:
                        animCurr = animSouth;
                        break;
                    case Input.Keys.S:
                        animCurr = animNorth;
                        break;
                }
                return true;
            }
        });
        setBounds(0,0,currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animCurr.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, 50, 50);
    }
}
