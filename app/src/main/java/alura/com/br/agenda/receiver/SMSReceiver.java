package alura.com.br.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.telephony.SmsMessage;
import android.widget.Toast;

import alura.com.br.agenda.R;
import alura.com.br.agenda.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];


        SmsMessage sms = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String formato = (String) intent.getSerializableExtra("format");
            sms = SmsMessage.createFromPdu((byte[]) pdus[0], formato);
        } else {
            sms = SmsMessage.createFromPdu((byte[]) pdus[0]);
        }

        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);
        if(dao.ehAluno(telefone)) {
            Toast.makeText(context, "Chegou um SMS!", Toast.LENGTH_SHORT).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }

        dao.close();
    }
}
