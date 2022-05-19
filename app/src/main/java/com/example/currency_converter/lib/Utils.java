package com.example.currency_converter.lib;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

import com.example.currency_converter.R;

public class Utils
{

    /**
     * Shows an Android (nicer) equivalent to JOptionPane
     * @param context  The Context from which we can get access to resources
     * @param strTitle Title of the Dialog box
     * @param strMsg   Message (body) of the Dialog box
     */
    public static void showInfoDialog (Context context, String strTitle, String strMsg)
    {
        // create the listener for the dialog
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener ()
        {
            @Override
            public void onClick (DialogInterface dialog, int which)
            {
                //nothing needed to do here
            }
        };

        // Create the AlertDialog.Builder object
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (context);

        // Use the AlertDialog's Builder Class methods to set the title, icon, message, et al.
        // These could all be chained as one long statement, if desired
        alertDialogBuilder.setTitle (strTitle);
        alertDialogBuilder.setIcon (R.mipmap.ic_launcher_round);
        alertDialogBuilder.setMessage (strMsg);
        alertDialogBuilder.setCancelable (true);
        alertDialogBuilder.setNeutralButton (context.getText (android.R.string.ok), listener);

        // Create and Show the Dialog
        alertDialogBuilder.show ();
    }

    /**
     * Overloaded XML version of showInfoDialog(Context context, String, String) method
     * @param context  The Context from which we can get access to resources
     * @param titleID Title stored in XML resource (e.g. strings.xml)
     * @param msgID   Message (body) stored in XML resource (e.g. strings.xml)
     */
    public static  void showInfoDialog (Context context, int titleID, int msgID)
    {
        showInfoDialog (context,
                context.getText(titleID).toString (), context.getText(msgID).toString ());
    }


}
