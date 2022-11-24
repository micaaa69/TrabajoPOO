package adaptador;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.trabajopoo.Facultad;

public class AdaptadorFacultades extends ArrayAdapter<Facultad> {

    public AdaptadorFacultades(@NonNull Context context, int resource) {
        super(context, resource);

    }
}
