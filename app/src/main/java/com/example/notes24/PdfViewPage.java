package com.example.notes24;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URLEncoder;

public class PdfViewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view_page);

        WebView webView = findViewById(R.id.webView);

        String pdf_url = getIntent().getStringExtra("pdf_url");
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("PDF");
        pd.setMessage("Loading...");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String url = "";
        try {
            url = URLEncoder.encode(pdf_url,"UTF-8");
        }catch (Exception ex){}
        
        if(url.equals("")) {
            Toast.makeText(this, "Kuch nahi hai broo", Toast.LENGTH_SHORT).show();
        }
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
    }
}