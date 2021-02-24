package com.example.myapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.network.RemoteRepository;
import com.example.myapplication.roomDB.LocalRepository;

public class ViewmodelFactory extends ViewModelProvider.NewInstanceFactory {

    Application application;
    RemoteRepository remoteRepository;
    LocalRepository localRepository;
      public ViewmodelFactory(Application application) {
          this.application = application;
    }


    public <T extends ViewModel> T create(int selection) {

        if(selection==0){
            remoteRepository =new RemoteRepository();
            return (T) new RepoViewModel(remoteRepository);
        }else if(selection==1){
            localRepository =new LocalRepository(application);
            return (T) new DbViewModel(localRepository);
        }else{
            return null;
        }
    }
}
