package adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.example.trabajopoo.Alternativa;
import com.example.trabajopoo.R;

import java.util.ArrayList;

public class AdaptadorAlternativa extends BaseAdapter {
    private  Context mContext;
    private ArrayList mArrayList;

    private RadioButton rdbtn1, rdbtn2, rdbtn3, rdbtn4, rdbtn5;

    public AdaptadorAlternativa(Context mContext, ArrayList<Alternativa> alternativas){
        this.mContext = mContext;
        this.mArrayList = alternativas;
    }

    @Override
    public int getCount() {
        return  this.mArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return  this.mArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.alternativas, null);
        rdbtn1 = view.findViewById(R.id.radio1);

        rdbtn2 = view.findViewById(R.id.radio2);
        rdbtn3 = view.findViewById(R.id.radio3);
        rdbtn4 = view.findViewById(R.id.radio4);
        rdbtn5 = view.findViewById(R.id.radio5);



        return view;
    }
}
