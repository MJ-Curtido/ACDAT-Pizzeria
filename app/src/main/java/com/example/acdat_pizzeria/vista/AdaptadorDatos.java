package com.example.acdat_pizzeria.vista;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.enums.TipoNombre;
import com.example.acdat_pizzeria.enums.TipoTamanyo;
import com.example.acdat_pizzeria.servicio.Servicio;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolder> {
    private List<ItemLista> mDataSet;
    private Context context;
    private ArrayList<Pizza> pizzasPred;
    private Usuario usuario;
    private TipoTamanyo tamanyo;

    public AdaptadorDatos(Context context) {
        this.context = context;
        mDataSet = new ArrayList<ItemLista>();

        pizzasPred = Servicio.getInstance().obtenerPizzasPred(context);
    }

    public void add(ItemLista i, Usuario usuario, TipoTamanyo tamanyo) {
        mDataSet.add(i);
        notifyItemInserted(mDataSet.indexOf(i));

        this.usuario = usuario;
        this.tamanyo = tamanyo;
    }

    @NonNull
    @Override
    public AdaptadorDatos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDatos.ViewHolder holder, int position) {
        ItemLista itemLista = mDataSet.get(position);
        holder.imagen.setImageDrawable(itemLista.getFotoPizza());
        holder.texto.setText(itemLista.getTextoPizza());
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza pizza = pizzasPred.get(position);
                pizza.setTamanyo(tamanyo);
                pizza.setUsuario(usuario);
                pizza.setNombre(TipoNombre.PERSONALIZADA);
                pizza.calcularPrecio();

                Intent i = new Intent(context, Pedir.class);
                i.putExtra("usuarioActual", usuario);
                i.putExtra("tamanyoPizza", tamanyo);
                i.putExtra("pizzaPedido", pizza);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imagen;
        protected TextView texto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imagen = (ImageView) itemView.findViewById(R.id.imgLista);
            this.texto = (TextView) itemView.findViewById(R.id.txtLista);
        }
    }
}
