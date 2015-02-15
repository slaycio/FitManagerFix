package pl.altoriis.fitmanagerfix;

import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.content.Context;
import android.os.Message;


import com.sec.android.app.shealthlite.datamanager.SHealthLiteSyncManager;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;


    //SM-G313F

    // to wyglada na jakas funcke kjtra mozna uzyc
    //com.sec.android.app.shealthlite.dashboard.SHealthLiteDashboard
    //onServiceConnected

public class FitMangerFix implements IXposedHookLoadPackage {



    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.contains("sec") ||lpparam.packageName.contains("samsung") ) {
            XposedBridge.log("package: " + lpparam.packageName);
        }

        if (!lpparam.packageName.equals("com.sec.android.app.shealthlite"))
            return;


            XposedBridge.log("com.sec.android.app.shealthlite inside");

            //szukanie
            String[][] lista = new String[1][2];

            lista[0][0] = "com.sec.android.app.shealthlite.dashboard.SHealthLiteDashboard";
            lista[0][1] = "action_sync_now";

            for(final String[] x : lista) {

                boolean bSHealthLiteDashboardExists = false;
                try {

                    bSHealthLiteDashboardExists = XposedHelpers.findClass("com.sec.android.app.shealthlite.dashboard.SHealthLiteDashboard"
                            , lpparam.classLoader) == null ? false : true;
                } catch (Exception e) {
                    XposedBridge.log("nie ma klasy nie ma");

                } 
/*
                try {
                    XposedBridge.log("proba" + x[0] + ":::" + x[1]);

                    findAndHookMethod(x[0], lpparam.classLoader, x[1],
                            XC_MethodReplacement.DO_NOTHING);
                } catch (Throwable t) {
                    XposedBridge.log(t);
                }
*/

                if (bSHealthLiteDashboardExists) {
                    XposedBridge.log("bSHealthLiteDashboardExists");
                    try {

                        XposedBridge.log("a teraz TRY");


                        findAndHookMethod(x[0], lpparam.classLoader, x[1], new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                // this will be called before the clock was updated by the original method
                                XposedBridge.log(x[0] + "przed");

                            }

                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                // this will be called after the clock was updated by the original method


                                SHealthLiteSyncManager mProgress = (SHealthLiteSyncManager) getObjectField(param.thisObject, "mProgress");

                                Object state = XposedHelpers.callMethod(mCM, "getState");



                                XposedBridge.log(x[0] + " po");
                            }
                        });

                        XposedBridge.log("po zmiana metody");
                    } catch (Throwable t) {
                        XposedBridge.log("wywalilisie");
                        XposedBridge.log(t);
                    }

                }







        }




    }
}