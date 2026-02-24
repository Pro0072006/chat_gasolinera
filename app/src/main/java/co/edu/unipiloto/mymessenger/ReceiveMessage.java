package co.edu.unipiloto.mymessenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiveMessage extends AppCompatActivity {
    TextView chatHistory;
    EditText messageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        chatHistory = findViewById(R.id.chatHistory);
        messageInput = findViewById(R.id.messageInput);

        updateChat();
    }

    public void onSendMessage(View view){
        String text = messageInput.getText().toString();

        if(!text.isEmpty()){
            ChatStorage.messages.add(new Message("Estacion", text));

            Intent intent = new Intent(this, CreateMessageActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateChat();
    }

    private void updateChat(){
        StringBuilder builder = new StringBuilder();
        for(Message m : ChatStorage.messages){
            builder.append(m.sender)
                    .append(": ")
                    .append(m.text)
                    .append("\n\n");
        }
        chatHistory.setText(builder.toString());
    }
}