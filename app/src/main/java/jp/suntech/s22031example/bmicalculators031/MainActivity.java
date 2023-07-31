package jp.suntech.s22031example.bmicalculators031;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btExecution = findViewById(R.id.bt_Execution);
        Button btClear = findViewById(R.id.bt_Clear);

        ClickListener listener = new ClickListener();

        btExecution.setOnClickListener(listener);
        btClear.setOnClickListener(listener);
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            EditText inputAge = findViewById(R.id.et_Age);
            EditText inputHeight = findViewById(R.id.et_Height);
            EditText inputWeight = findViewById(R.id.et_Weight);

            TextView output1 = findViewById(R.id.tv_output1);
            TextView output2 = findViewById(R.id.tv_output2);
            TextView output3 = findViewById(R.id.tv_output3);
            TextView output4 = findViewById(R.id.tv_output4);

            int id = view.getId();

            //float型に変換
            float Agefl = Float.parseFloat(inputAge.getText().toString());
            float Heightfl = Float.parseFloat(inputHeight.getText().toString());
            float Weightfl = Float.parseFloat(inputWeight.getText().toString());

            // 計算
            if(id == R.id.bt_Execution) {
                if (Agefl < 16) {
                    ExecutionDialogFragment dialogFragment = new ExecutionDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "ExecutionDialogFlagment");
                }else ;

                float BMI = 0;
                BMI = Weightfl / ((Heightfl / 100) * (Heightfl / 100));
                double Appropriate = (Heightfl/100) * (Heightfl/100) * 22;
                Appropriate =((double) Math.round(Appropriate * 10)) / 10;

                output1.setText("あなたの肥満度は");

                if(BMI >= 40){
                    output2.setText("肥満(4度)");
                }else if(BMI >= 35 && BMI < 40){
                    output2.setText("肥満(3度)");
                }else if(BMI >= 30 && BMI < 35){
                    output2.setText("肥満(2度)");
                }else if(BMI >= 25 && BMI < 30){
                    output2.setText("肥満(1度)");
                }else if(BMI >= 18.5 && BMI < 35){
                    output2.setText("普通体重");
                }else if(BMI < 18.5){
                    output2.setText("低体重");
                }

                output3.setText("あなたの適正体重は");
                output4.setText(Appropriate + "kg");

            }
            // クリア
            else if(id == R.id.bt_Clear){
                inputAge.setText("");
                inputHeight.setText("");
                inputWeight.setText("");
            }
        }
    }
}