#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_francisco_trial_MensajeActivity_getResultJNI(JNIEnv *env, jobject instance, jint param) {

    jstring  resultado = "";
    // TODO
    if (param == 1){
        resultado = "1";
    }
    else if(param == 2){
        resultado = "2";
    }

    return (*env)->NewStringUTF(env, resultado);
}