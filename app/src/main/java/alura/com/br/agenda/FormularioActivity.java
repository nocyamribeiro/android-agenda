package alura.com.br.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import alura.com.br.agenda.dao.AlunoDAO;
import alura.com.br.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        Button botaoSalvar = (Button) findViewById(R.id.formulario_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Aluno aluno = helper.pegaAluno();
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() +" Salvo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.formulario, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() +" Salvo!", Toast.LENGTH_SHORT).show();
                AlunoDAO alunoDAO = new AlunoDAO(this);
                alunoDAO.insere(aluno);
                alunoDAO.close();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
