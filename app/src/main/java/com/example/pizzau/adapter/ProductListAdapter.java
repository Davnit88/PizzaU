package com.example.pizzau.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import com.example.pizzau.product.Product;
import com.example.pizzau.R;

public class ProductListAdapter extends BaseAdapter {
    private List<Product> productos;
    private LayoutInflater inflater;
    private OnEditClickListener editClickListener;
    private OnDeleteClickListener deleteClickListener;
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener=listener;

    }
    public interface OnEditClickListener{
        void onEditClick(int position);
    }
    public interface OnDeleteClickListener{
        void onDeleteClick(int position);
    }
    public void setOnEditClickListener(OnEditClickListener listener){
        this.editClickListener=listener;
    }
    public ProductListAdapter(Context context , List<Product>productos){
        this.productos=productos;
        this.inflater=LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView=inflater.inflate(R.layout.list_producto,parent,false);
            viewHolder =new ViewHolder();
            viewHolder.txtId=convertView.findViewById(R.id.etId);
            viewHolder.txtNombre=convertView.findViewById(R.id.etNombre);
            viewHolder.txtprecio=convertView.findViewById(R.id.etprice);
            viewHolder.btnEditar=convertView.findViewById(R.id.btnEditar);
            viewHolder.btnEliminar=convertView.findViewById(R.id.btnEliminar);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Product product =productos.get(position);
        viewHolder.txtId.setText(String.valueOf("ID de producto:"+product.getId()));
        viewHolder.txtNombre.setText("Nombre: "+product.getName());
        viewHolder.txtprecio.setText("Precio: "+product.getPrice());
        viewHolder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteClickListener!=null){
                    editClickListener.onEditClick(position);

                }
            }
        });
        viewHolder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteClickListener != null){
                    deleteClickListener.onDeleteClick(position);
                }
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView txtId;
        TextView txtNombre;
        TextView txtprecio;
        Button btnEditar;
        Button btnEliminar;
    }
}