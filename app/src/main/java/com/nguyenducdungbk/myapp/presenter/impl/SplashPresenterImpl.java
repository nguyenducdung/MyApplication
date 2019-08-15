package com.nguyenducdungbk.myapp.presenter.impl;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenducdungbk.myapp.interactor.SplashInteractor;
import com.nguyenducdungbk.myapp.network.response.Contact;
import com.nguyenducdungbk.myapp.presenter.SplashPresenter;
import com.nguyenducdungbk.myapp.view.SplashView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class SplashPresenterImpl extends BasePresenterImpl<SplashView> implements SplashPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final SplashInteractor mInteractor;

    private List<Contact> data = new ArrayList<>();
    private List<String> response = new ArrayList<>();

    // The view is available using the mView variable

    @Inject
    public SplashPresenterImpl(@NonNull SplashInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated) {
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("contacts");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            response.add(dataSnapshot1.getValue().toString());
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}