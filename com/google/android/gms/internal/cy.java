package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class cy
  extends WebChromeClient
{
  private final cw gv;
  
  public cy(cw paramcw)
  {
    this.gv = paramcw;
  }
  
  private static void a(AlertDialog.Builder paramBuilder, String paramString, JsResult paramJsResult)
  {
    paramBuilder.setMessage(paramString).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.jd.confirm();
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.jd.cancel();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        this.jd.cancel();
      }
    }).create().show();
  }
  
  private static void a(final Context paramContext, AlertDialog.Builder paramBuilder, String paramString1, String paramString2, JsPromptResult paramJsPromptResult)
  {
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    localLinearLayout.setOrientation(1);
    TextView localTextView = new TextView(paramContext);
    localTextView.setText(paramString1);
    paramContext = new EditText(paramContext);
    paramContext.setText(paramString2);
    localLinearLayout.addView(localTextView);
    localLinearLayout.addView(paramContext);
    paramBuilder.setView(localLinearLayout).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.je.confirm(paramContext.getText().toString());
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.je.cancel();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        this.je.cancel();
      }
    }).create().show();
  }
  
  private static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, JsResult paramJsResult, JsPromptResult paramJsPromptResult, boolean paramBoolean)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString1);
    if (paramBoolean) {
      a(paramContext, localBuilder, paramString2, paramString3, paramJsPromptResult);
    }
    for (;;)
    {
      return true;
      a(localBuilder, paramString2, paramJsResult);
    }
  }
  
  protected final void a(View paramView, int paramInt, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    bk localbk = this.gv.aB();
    if (localbk == null)
    {
      ct.v("Could not get ad overlay when showing custom view.");
      paramCustomViewCallback.onCustomViewHidden();
      return;
    }
    localbk.a(paramView, paramCustomViewCallback);
    localbk.setRequestedOrientation(paramInt);
  }
  
  public final void onCloseWindow(WebView paramWebView)
  {
    if (!(paramWebView instanceof cw))
    {
      ct.v("Tried to close a WebView that wasn't an AdWebView.");
      return;
    }
    paramWebView = ((cw)paramWebView).aB();
    if (paramWebView == null)
    {
      ct.v("Tried to close an AdWebView not associated with an overlay.");
      return;
    }
    paramWebView.close();
  }
  
  public final boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
  {
    String str = "JS: " + paramConsoleMessage.message() + " (" + paramConsoleMessage.sourceId() + ":" + paramConsoleMessage.lineNumber() + ")";
    switch (7.jg[paramConsoleMessage.messageLevel().ordinal()])
    {
    default: 
      ct.t(str);
    }
    for (;;)
    {
      return super.onConsoleMessage(paramConsoleMessage);
      ct.s(str);
      continue;
      ct.v(str);
      continue;
      ct.t(str);
      continue;
      ct.r(str);
    }
  }
  
  public final boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
  {
    WebView.WebViewTransport localWebViewTransport = (WebView.WebViewTransport)paramMessage.obj;
    paramWebView = new WebView(paramWebView.getContext());
    paramWebView.setWebViewClient(this.gv.aC());
    localWebViewTransport.setWebView(paramWebView);
    paramMessage.sendToTarget();
    return true;
  }
  
  public final void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    long l = 5242880L - paramLong3;
    if (l <= 0L)
    {
      paramQuotaUpdater.updateQuota(paramLong1);
      return;
    }
    if (paramLong1 == 0L) {
      if ((paramLong2 > l) || (paramLong2 > 1048576L)) {}
    }
    for (;;)
    {
      paramQuotaUpdater.updateQuota(paramLong2);
      return;
      paramLong2 = 0L;
      continue;
      if (paramLong2 == 0L)
      {
        paramLong2 = Math.min(Math.min(131072L, l) + paramLong1, 1048576L);
      }
      else
      {
        paramLong3 = paramLong1;
        if (paramLong2 <= Math.min(1048576L - paramLong1, l)) {
          paramLong3 = paramLong1 + paramLong2;
        }
        paramLong2 = paramLong3;
      }
    }
  }
  
  public final void onHideCustomView()
  {
    bk localbk = this.gv.aB();
    if (localbk == null)
    {
      ct.v("Could not get ad overlay when hiding custom view.");
      return;
    }
    localbk.X();
  }
  
  public final boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, null, paramJsResult, null, false);
  }
  
  public final boolean onJsBeforeUnload(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, null, paramJsResult, null, false);
  }
  
  public final boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, null, paramJsResult, null, false);
  }
  
  public final boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, paramString3, null, paramJsPromptResult, true);
  }
  
  public final void onReachedMaxAppCacheSize(long paramLong1, long paramLong2, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    paramLong1 = 131072L + paramLong1;
    if (5242880L - paramLong2 < paramLong1)
    {
      paramQuotaUpdater.updateQuota(0L);
      return;
    }
    paramQuotaUpdater.updateQuota(paramLong1);
  }
  
  public final void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    a(paramView, -1, paramCustomViewCallback);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\cy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */