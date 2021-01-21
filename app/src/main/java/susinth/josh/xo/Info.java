package susinth.josh.xo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Info extends AppCompatActivity {
    private TextView infoLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        infoLink=findViewById(R.id.txt_link);
        infoLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}