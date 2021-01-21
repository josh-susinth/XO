package susinth.josh.xo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_reset;
    private Button buttons[];

    private TextView X_player,O_player;
    private TextView xIndicator,oIndicator;

    private ImageView img_info;

    private int X_score,O_score,moves;

    private boolean xTurn=true;

    private Button btn_0,btn_1,btn_2,
            btn_3,btn_4,btn_5,
            btn_6,btn_7,btn_8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_reset=findViewById(R.id.btn_reset);

        xIndicator=findViewById(R.id.txt_xTurn_indicator);
        oIndicator=findViewById(R.id.txt_oTurn_indicator);

        btn_0=findViewById(R.id.btn_0);
        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
        btn_3=findViewById(R.id.btn_3);
        btn_4=findViewById(R.id.btn_4);
        btn_5=findViewById(R.id.btn_5);
        btn_6=findViewById(R.id.btn_6);
        btn_7=findViewById(R.id.btn_7);
        btn_8=findViewById(R.id.btn_8);

        X_player=findViewById(R.id.txt_Xscore);
        O_player=findViewById(R.id.txt_Oscore);

        img_info=findViewById(R.id.img_info);
        img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Info.class);
                startActivity(intent);
            }
        });

        //indicating turn
        indicator();

        buttons= new Button[]{btn_0, btn_1, btn_2,
                btn_3, btn_4, btn_5,
                btn_6, btn_7, btn_8};

        for(int i=0;i<9;i++){
            buttons[i].setOnClickListener(this);
        }


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   reset();
                X_player.setText("X score : 0");
                O_player.setText("O score : 0");
                X_score=0;
                O_score=0;

            }
        });
    }

    @Override
    public void onClick(View v) {
        indicator();
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(xTurn){
            ((Button) v).setText("X");

        }
        else{
            ((Button) v).setText("O");
        }
        moves+=1;
        if(checkWin()){
            if(xTurn){
                Toast.makeText(this,"X wins !",Toast.LENGTH_SHORT).show();
                X_score++;
                X_player.setText("X score : "+X_score);
                reset();
            }
            else{
                Toast.makeText(this,"O wins !",Toast.LENGTH_SHORT).show();
                O_score++;
                O_player.setText("O score : "+O_score);
                reset();
            }
        }
        else if(moves==9){
            draw();
        }
        else{
            xTurn=!xTurn;
            indicator();
        }
    }
    private void indicator(){
        if(xTurn){
            xIndicator.setText(".");
            oIndicator.setText("");
        }
        else{
            xIndicator.setText("");
            oIndicator.setText(".");
        }
    }
    private void draw(){
        Toast.makeText( this,"DRAW !", Toast.LENGTH_SHORT).show();
        reset();
    }
    private void reset(){

        for(Button button:buttons){
            button.setText("");
        }
        xTurn=!xTurn;
        indicator();
        moves=0;
    }
    private boolean checkWin(){
        String[] field=new String[9];
        for(int i=0;i<9;i++){
            field[i]=buttons[i].getText().toString();
        }

        //horizontal check
        if(field[0].equals(field[1])
                && field[0].equals(field[2]) && !field[0].equals("")){
            return true;
        }
        if(field[3].equals(field[4])
                && field[3].equals(field[5]) && !field[3].equals("")){
            return true;
        }
        if(field[6].equals(field[7])
                && field[6].equals(field[8]) && !field[6].equals("")){
            return true;
        }

        //vertical
        if(field[0].equals(field[3])
                && field[0].equals(field[6]) && !field[0].equals("")){
            return true;
        }
        if(field[1].equals(field[4])
                && field[1].equals(field[7]) && !field[1].equals("")){
            return true;
        }
        if(field[2].equals(field[5])
                && field[2].equals(field[8]) && !field[2].equals("")){
            return true;
        }

        //diagonal
        if(field[0].equals(field[4])
                && field[0].equals(field[8]) && !field[0].equals("")){
            return true;
        }
        if(field[2].equals(field[4])
                && field[2].equals(field[6]) && !field[2].equals("")){
            return true;
        }

        return false;



    }
}