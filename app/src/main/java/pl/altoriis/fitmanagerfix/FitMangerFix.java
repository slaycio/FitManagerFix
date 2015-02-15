package pl.altoriis.fitmanagerfix;

import static de.robv.android.xposed.XposedHelpers.getAdditionalInstanceField;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.content.Context;
import android.os.Message;
import com.sec.android.app.shealthlite.datamanager.SHealthLitePedometerManager;


import com.sec.android.app.shealthlite.datamanager.SHealthLiteSyncManager;
import com.sec.android.app.shealthlite.service.SHealthService;
import com.sec.android.app.shealthlite.service.SHealthWatchdog;

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

            lista[0][0] = "com.sec.android.app.shealthlite.service.SHealthService";
            lista[0][1] = "get_mode";

        //request_latest_data_send   - znajduje ale nie uruchamia jej
        //get_mode
        //getInstance





            for(final String[] x : lista) {

                boolean bExists = false;
                try {

                    bExists = XposedHelpers.findClass(x[0]
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

                if (bExists) {
                    XposedBridge.log("bExists");
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


                              //  Object result = XposedHelpers.callMethod(param.thisObject, "do_sync_pedometer_prepare");

                               // XposedBridge.log("test1" + result.getClass().toString());
                                XposedBridge.log("test2"+ param.thisObject.getClass().toString());
                                XposedBridge.log("test3" + param.getResult().toString());
                               // param.setResult(0);



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