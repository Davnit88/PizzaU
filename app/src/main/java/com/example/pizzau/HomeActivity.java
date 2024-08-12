package com.example.pizzau;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.example.pizzau.accessproduct.ProductAP;
import com.example.pizzau.product.Product;
import com.example.pizzau.adapter.ProductListAdapter;

public class HomeActivity extends AppCompatActivity {

    private ProductAP productAP;
    private EditText etlNombre ,etlprice;
    private Button btnAgregar, btnMostrar;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        productAP = new ProductAP(this);
        productAP.abrir();

        etlNombre = findViewById(R.id.etlNombre);
        etlprice = findViewById(R.id.etlprecio);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnMostrar = findViewById(R.id.btnMostrar);
        listView = findViewById(R.id.listView);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProducto();
            }


        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarProductos();
                btnMostrar.setVisibility(listView.GONE);
            }
        });
    }

    private void mostrarProductos() {
        List<Product>productos=productAP.obtnTodProductos();
        StringBuilder stringBuilder = new StringBuilder();
        for(Product product : productos){
            stringBuilder.append("ID: ").append(product.getId()).append(", Nombre: ").append(product.getName().toString()).append(", precio: ").append(product.getPrice()).append("\n");
        }
        ProductListAdapter adapter = new ProductListAdapter(this, productos);
        listView.setAdapter(adapter);

        adapter.setOnEditClickListener(new ProductListAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(int position) {
                Product producto= productos.get(position);
                etlNombre.setText(producto.getName());
                etlprice.setText(producto.getPrice());
                int idProducto =  producto.getId();
                btnAgregar.setText("guardar");
                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarProducto(idProducto);
                    }
                });
            }

        });
        adapter.setOnDeleteClickListener(new ProductListAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Product product = productos.get(position);
                eliminarProducto(product.getId());
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void agregarProducto() {
        String nombre = etlNombre.getText().toString();
        String precio = etlprice.getText().toString();
        if (!nombre.isEmpty()&& !precio.isEmpty()){
            long resultado= productAP.insertarProduct(nombre, Float.valueOf(precio));
            if(resultado !=1){
                Toast.makeText(HomeActivity.this, "Producto Agregado exitosamente", Toast.LENGTH_SHORT).show();
                etlNombre.setText("");
                etlprice.setText("");
            }else {
                Toast.makeText(HomeActivity.this, "Error al agregar Producto", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(HomeActivity.this, "COMPLETE LOS CAMPOS", Toast.LENGTH_SHORT).show();
        }
        actualizarListaProducto();
    }
    private void actualizarProducto(int idUsuario){
        String nombre = etlNombre.getText().toString();
        String precio = etlprice.getText().toString();
        if (!nombre.isEmpty()&& !precio.isEmpty()){
            productAP.actualizarProducto(idUsuario,nombre,precio);
            Toast.makeText(HomeActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            etlNombre.setText(" ");
            etlprice.setText("");
            btnAgregar.setText("Agregar");
            btnAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    agregarProducto();
                }
            });
        }
        actualizarListaProducto();
    }
    private void eliminarProducto(int idUsuario){
        productAP.eliminarProducto(idUsuario);
        Toast.makeText(HomeActivity.this, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show();
        actualizarListaProducto();
    }
    private void actualizarListaProducto(){
        mostrarProductos();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        productAP.cerrar();
    }

}
