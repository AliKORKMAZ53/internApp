package com.example.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewmodelFactory extends ViewModelProvider.NewInstanceFactory {
    @NonNull
    private final Application application;

    public ViewmodelFactory(@NonNull Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RepoViewModel();
    }
    @NonNull
    public <T extends ViewModel> T DBcreate() {
        return (T) new DbViewModel(application);
    }



}
