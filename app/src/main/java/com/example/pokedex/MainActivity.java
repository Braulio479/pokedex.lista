package com.example.pokedex;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazamos con el WebView del diseño XML
        webView = findViewById(R.id.miWebView);

        // Configuraciones necesarias para que funcione JavaScript y tu pokemon.json
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // Permite guardar datos locales (como favoritos)
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);

        webView.setWebViewClient(new WebViewClient());

        // Cargamos tu archivo web local desde la carpeta assets
        webView.loadUrl("file:///android_asset/index.html");

        // OPCIÓN 2: Control del botón "Atrás" integrado correctamente dentro de onCreate
        getOnBackPressedDispatcher().addCallback(this, new androidx.activity.OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView != null && webView.canGoBack()) {
                    webView.goBack(); // Navega hacia atrás en tu Pokédex web
                } else {
                    finish(); // Cierra la aplicación si está en la pantalla principal
                }
            }
        });
    }
}