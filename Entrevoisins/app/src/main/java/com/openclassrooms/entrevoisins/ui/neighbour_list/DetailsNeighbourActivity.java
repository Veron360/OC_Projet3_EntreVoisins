package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.BUNDLE_EXTRA_NEIGHBOUR;

public class DetailsNeighbourActivity extends AppCompatActivity {


    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @BindView(R.id.neighbours_image_profile)
    ImageView imageView;

    @BindView(R.id.neighbours_name_Profile)
    TextView nameInUserImage;

    @BindView(R.id.neighbours_name)
    TextView name_user;

    @BindView(R.id.user_adresse)
    TextView user_adresse;

    @BindView(R.id.phone)
    TextView user_phone;

    @BindView(R.id.mail)
    TextView user_facebook;

    @BindView(R.id.user_message)
    TextView user_message;

    @BindView(R.id.button_favorites)
    ImageButton btn_favorites;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_neighbours);
        ButterKnife.bind(this);


        mApiService= DI.getNeighbourApiService();
        mNeighbour = (Neighbour) getIntent().getSerializableExtra(BUNDLE_EXTRA_NEIGHBOUR);
        nameInUserImage.setText(mNeighbour.getName());
        name_user.setText(mNeighbour.getName());
        user_adresse.setText(mNeighbour.getAdresse());
        user_phone.setText(mNeighbour.getPhone());
        user_facebook.setText(mNeighbour.getFacebook());
        user_message.setText(mNeighbour.getMessage());


        Glide.with(imageView.getContext())
                .load(mNeighbour.getAvatarUrl())
                .into(imageView);
        starColor();

        btn_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.changeFavorite(mNeighbour);
                mNeighbour.setFavorite(!mNeighbour.isFavorite());
                starColor();
            }
        });
    }


    private void starColor(){
        if(mNeighbour.isFavorite()){
            btn_favorites.setImageResource(R.drawable.ic_star_white_24dp);
        }else{
            btn_favorites.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }
}