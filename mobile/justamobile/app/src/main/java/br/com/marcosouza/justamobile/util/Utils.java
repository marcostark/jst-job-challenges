package br.com.marcosouza.justamobile.util;

import android.content.Context;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.marcosouza.justamobile.model.CollectionPoints;

public class Utils {

    public static void messageConnectFailed(Context context, Throwable e){
        Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    /* Apenas teste*/
    public static List<CollectionPoints> getMarkers(){
        List<CollectionPoints> markers = Arrays.asList(
                new CollectionPoints("Exposição","",
                        "Recolhimento de Pneus",
                        "Material: Papelão , Plástico, Pneus",
                        "-7.995175",
                        "-38.301040"),

                new CollectionPoints("UAST","",
                        "Material: Papelão , Plástico",
                        "Material: Papelão , Plástico",
                        "-7.956207",
                        "-38.295732"),

                new CollectionPoints("Praça da Matriz",
                        "","Material: Papelão , Plástico",
                        "Material: Papelão , Plástico",
                        "-7.991350",
                        "-38.299877"),

                new CollectionPoints("Praça do IPSEP",
                        "","Material: Papelão , Plástico",
                        "Material: Papelão , Plástico",
                        "-7.990006",
                        "-38.285883")
        );
        return markers;
    }

}
