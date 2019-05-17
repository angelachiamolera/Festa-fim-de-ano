package festafimdeano.angela.com.festafimdeano.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.text.SimpleDateFormat;

import festafimdeano.angela.com.festafimdeano.R;
import festafimdeano.angela.com.festafimdeano.constant.FimDeAnoConstant;
import festafimdeano.angela.com.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.check_participate) {


            if (this.mViewHolder.checkParticipate.isChecked()) {
                // Salvar presenca
                this.mSecurityPreferences.storeString(FimDeAnoConstant.PRESENCE_KEY, FimDeAnoConstant.CONFIRMATION_YES);
            } else {
                // Salvar a ausencia

                this.mSecurityPreferences.storeString(FimDeAnoConstant.PRESENCE_KEY, FimDeAnoConstant.CONFIRMATION_NO);


            }
        }
    }

    private void loadDataFromActivity(){

        Bundle extras =  getIntent().getExtras();

        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstant.PRESENCE_KEY);

            if (presence != null && presence.equals(FimDeAnoConstant.CONFIRMATION_YES)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            }else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {

        CheckBox checkParticipate;
        }
}
