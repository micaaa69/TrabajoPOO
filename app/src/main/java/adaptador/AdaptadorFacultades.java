package adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.trabajopoo.Facultad;

public class AdaptadorFacultades extends ArrayAdapter<Facultad> {
    Facultad[] facultades;
    Context mContext;
    int resource;
    public AdaptadorFacultades(@NonNull Context context, int resource, @NonNull Facultad[] objects) {
        super(context, resource, objects);
        this.facultades = objects;
        this.mContext = context;
        this.resource = resource;

    }

}
