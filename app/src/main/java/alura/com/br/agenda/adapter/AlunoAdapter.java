package alura.com.br.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import alura.com.br.agenda.ListaAlunosActivity;
import alura.com.br.agenda.R;
import alura.com.br.agenda.modelo.Aluno;

public class AlunoAdapter extends BaseAdapter{

    private final List<Aluno> alunos;
    private final Context context;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(alunos != null) {
            return alunos.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(alunos != null) {
            return alunos.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(alunos != null) {
            return alunos.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if(view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        Bitmap bm = BitmapFactory.decodeFile(caminhoFoto);
        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        campoFoto.setImageBitmap(bm);
        campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        campoFoto.setTag(caminhoFoto);

        return view;
    }
}
