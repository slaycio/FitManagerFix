package pl.altoriis.fitmanagerfix;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by p on 2015-01-25.
 */
// SM-900S - device
//SM-G313F
    // MODEL GT-I9505
    //


    // to wyglada na jakas funcke kjtra mozna uzyc
    //ACTION = com.sec.android.app.shealthlite.syncstartfullysyncrefresh

public class FitMangerFix implements IXposedHookLoadPackage {



    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.contains("sec") ||lpparam.packageName.contains("samsung") ) {
            XposedBridge.log("package: " + lpparam.packageName);
        }

        if (lpparam.packageName.equals("com.samsung.android.wms")) {
            XposedBridge.log("com.samsung.android.wms inside");

            //szukanie
            String[][] lista = new String[1][2];

            lista[0][0] = "com.samsung.android.wms.app.ClocksFragment";
            lista[0][1] = "onResume";

/*
            lista[1][0] = "com.samsung.android.wms.app.PairedWingtipFragment";
            lista[1][1] = "onClick";
            lista[2][0] = "com.samsung.android.wms.app.PairedWingtipFragment";
            lista[2][1] = "onActivityResult";
            lista[3][0] = "com.samsung.android.wms.app.PairedWingtipFragment";
            lista[3][1] = "onCreate";
            lista[4][0] = "com.samsung.android.wms.app.ClocksFragment";
            lista[4][1] = "onItemClick";
*/


            for(final String[] x : lista) {

/*
                try {
                    XposedBridge.log("proba" + x[0] + ":::" + x[1]);

                    findAndHookMethod(x[0], lpparam.classLoader, x[1],
                            XC_MethodReplacement.DO_NOTHING);
                } catch (Throwable t) {
                    XposedBridge.log(t);
                }
*/


                try {

                    XposedBridge.log("przed zmiana metody");


                    findAndHookMethod(x[0], lpparam.classLoader, x[1], new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            // this will be called before the clock was updated by the original method
                            XposedBridge.log(x[0]+ "przed");
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            // this will be called after the clock was updated by the original method
                            XposedBridge.log(x[0]+" po");
                        }
                    });

                    XposedBridge.log("po zmiana metody");
                } catch (Throwable t) {
                    XposedBridge.log("wywalilisie");
                    XposedBridge.log(t);
                }

            }


        }

        if (lpparam.packageName.equals("com.sec.android.app.shealthlite")) {
            XposedBridge.log("com.sec.android.app.shealthlite inside");

            //szukanie
            String[][] lista = new String[1][2];

            lista[0][0] = "com.sec.android.app.shealthlite.SHealthLiteApplication";
            lista[0][1] = "onCreate";

            for(final String[] x : lista) {

/*
                try {
                    XposedBridge.log("proba" + x[0] + ":::" + x[1]);

                    findAndHookMethod(x[0], lpparam.classLoader, x[1],
                            XC_MethodReplacement.DO_NOTHING);
                } catch (Throwable t) {
                    XposedBridge.log(t);
                }
*/


                try {

                    XposedBridge.log("przed zmiana metody");


                    findAndHookMethod(x[0], lpparam.classLoader, x[1], new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            // this will be called before the clock was updated by the original method
                            XposedBridge.log(x[0]+ "przed");

                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            // this will be called after the clock was updated by the original method
                            XposedBridge.log(x[0]+" po");
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