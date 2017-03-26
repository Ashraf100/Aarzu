/*
package com.aarzu.waqt.ramzan.xtra;

import android.content.res.Resources;
import android.util.Log;

import com.aarzu.waqt.R;
import com.aarzu.waqt.model.AllTask;

import java.io.InputStream;

import io.realm.Realm;

*/
/**
 * Created by Drac Android on 3/26/2017.
 *//*


public class RealmImporter {
    Resources resources;

    TransactionTime transactionTime;

    public RealmImporter(Resources resources) {
        this.resources = resources;
    }

    public void importFromJson(){
        Realm realm = Realm.getDefaultInstance();

        //transaction timer
        transactionTime = new TransactionTime();
        transactionTime.setStart(System.currentTimeMillis());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                InputStream inputStream = resources.openRawResource(R.raw.people);
                try {
                    realm.createAllFromJson(AllTask.class, inputStream);
                    transactionTime.setEnd(System.currentTimeMillis());
                } catch (Exception e){
                    realm.cancelTransaction();
                }

            }
        });

        Log.d( "Realm","createAllFromJson Task completed in " + transactionTime.getDuration() + "ms" );
    }

}
*/
