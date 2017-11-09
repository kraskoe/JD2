package com.pvt.app.beans;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */
public class BraveKnight implements Knight {
    private Quest quest;

    public BraveKnight(Quest q){
        quest = q;
    }

    @Override
    public void embarkOnQuest(){
        quest.embark();
    }
}
