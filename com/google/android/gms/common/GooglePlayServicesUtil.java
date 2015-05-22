package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.internal.du;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.fg;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class GooglePlayServicesUtil
{
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 4242000;
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  static final byte[][] mD = { K("0?\004C0?\003+�\003\002\001\002\002\t\000��?FdJ0?0\r\006\t*?H?�\r\001\001\004\005\0000t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android0\036\027\r080821231334Z\027\r360107231334Z0t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android0?\001 0\r\006\t*?H?�\r\001\001\001\005\000\003?\001\r\0000?\001\b\002?\001\001\000�V.\000�;�\b�\n?o\022N)�\021?�V??X�?�\023\003�T�r�@�\033\035�\023\tgbNFV�wj?\031=�忷$�\036w\030?\016jG�;3�`?w\0301E?�{.Xft��V[\037LjYU�?Q�=���\\'\"\"R�u��\025Jd_?qh����\022�xWi�4�y?�~.�vL�?\007��qT��_d�\032D�\002�I\005AW�\002�_\\\016U��?\031�?'?�Q\026?Šo\031�???���ֹ?h?)y�\016\030�??k;?�??U*\016;L?�X�??��?�5�\003���\r?D��$�?38r�R!?^ڰ�\r\013\024[j�???y\002\001\003�?�0?�0\035\006\003U\035\016\004\026\004\024�}?�!\027V%?�?�k??��?�0?�\006\003U\035#\004??0???\024�}?�!\027V%?�?�k??��?��x�v0t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android?\t\000��?FdJ0?0\f\006\003U\035\023\004\0050\003\001\001�0\r\006\t*?H?�\r\001\001\004\005\000\003?\001\001\000m?R��?0,6\n��??�??�\004�]z\026a��F�?B\004?�Jh��\032S\036�YZb<�\007c�g)zz?W\022�\007?\b?�\020?)\022M{\020b\031�?�>���_�q�?&??�?�mD�٠?l�?\005�?��?D~??s\020v�E�?`\t�\031�a�&A�?'\035?R(��??]�E'X�a�?\f?�5.BL�6\\R52�2Q7Y<J?A��A��\r\013\020q��@???�\034�'�gCi??�/�\021�\006Ϳ,�\020�\017?:?Wb??H���LqD\027?B�\005?�?W:?[9\r�?�A?1?]_u?0\021&?�b\024\020�i0?"), K("0?\004�0?\003?�\003\002\001\002\002\t\000??�l}�N?0\r\006\t*?H?�\r\001\001\004\005\0000??1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*?H?�\r\001\t\001\026\023android@android.com0\036\027\r080415233656Z\027\r350901233656Z0??1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*?H?�\r\001\t\001\026\023android@android.com0?\001 0\r\006\t*?H?�\r\001\001\001\005\000\003?\001\r\0000?\001\b\002?\001\001\000��.\b\n��1M�?���\030\\�=3�\ftὶ��?\023�,\\9�V�F?=e����Bk\007Ũ�Z9?�g�k�?�'?K?\013\"\000\031?�)\025�r�m*0\033�o��\021:��?t5�m#�}���e��\037\n?��\n??QlN?\005\021?�|\fU\027[?u�H�j�?\b?�O?��??}�,\n5\"?�\006�?\030^�Uy��m\b\013\035a?���?����\007�E��h��??^T?�lS�\013\022\035��\016b\f\030?�aۼ??<d_/U��?u?@p�?qQ�6p�j?\032�^?�\030?Ḯ�)??f�?l�D�?�m\034\033\017\002\001\003�?�0?�0\035\006\003U\035\016\004\026\004\024?\034ž?LC<a?:\025�L�\003?O�0?�\006\003U\035#\004?�0?�?\024?\034ž?LC<a?:\025�L�\003?Oಡ??�??0??1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*?H?�\r\001\t\001\026\023android@android.com?\t\000??�l}�N?0\f\006\003U\035\023\004\0050\003\001\001�0\r\006\t*?H?�\r\001\001\004\005\000\003?\001\001\000\031�\f�\005�x??L\r}?##=@?z��\000\b\035[����� k\016\021 ?\006Al�D??\023?kJ��?$�?�\\nL�\001j\025?n�?]�Z^:\001\0006�?H?\020?�.\036a??g:;�m�\013w��)?�U?�L?]#?�\t��; +NZ\"�2cHJ#?�)�?\0319u?3�ت\026\017B?�?\026>????fC��?/��?33[�?�?k\"?ѭDB)�9�Nﭫ?e�?K>Q�?{fx{�\022??��?�#�O�?IL\002??\005\026\022�e)9>?F�Ż!?w�Q�_*�'��?�\n�\0035i?;??��|��>\022C�\013") };
  static final byte[][] mE = { K("0?\002R0?\001�\002\004I4?~0\r\006\t*?H?�\r\001\001\004\005\0000p1\0130\t\006\003U\004\006\023\002US1\0130\t\006\003U\004\b\023\002CA1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google, Inc1\0240\022\006\003U\004\013\023\013Google, Inc1\0200\016\006\003U\004\003\023\007Unknown0\036\027\r081202020758Z\027\r360419020758Z0p1\0130\t\006\003U\004\006\023\002US1\0130\t\006\003U\004\b\023\002CA1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google, Inc1\0240\022\006\003U\004\013\023\013Google, Inc1\0200\016\006\003U\004\003\023\007Unknown0??0\r\006\t*?H?�\r\001\001\001\005\000\003??\0000??\002??\000?H\003\031?��G&8N\004S�??\013�?�{%\004�� |LlD��\000��a\017���-�\0163?��k&���[?��??�����O~?\"�?��\\�=?)�se�\025\026AZ��a}??�\031�蠻�?�\027���&@?Q!��?w?�\000\0238\024\030?.�\"?�X\r\002\003\001\000\0010\r\006\t*?H?�\r\001\001\004\005\000\003??\000@?f?�1�C???a?&�s�?Ĺ?���?<�?P\036?�\034o�Y��`\\O?�m\034?�? Gl��?�? :�w\027�e-???\007\b�!m�DWY&I���ĻL??�?���A��XOd�_A\r\005)?[h??\024\035\n?��\021?�*\r�?�\f�-��"), K("0?\004�0?\003?�\003\002\001\002\002\t\000?~O?ֵ??0\r\006\t*?H?�\r\001\001\005\005\0000??1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*?H?�\r\001\t\001\026\023android@android.com0\036\027\r100120010135Z\027\r370607010135Z0??1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*?H?�\r\001\t\001\026\023android@android.com0?\001 0\r\006\t*?H?�\r\001\001\001\005\000\003?\001\r\0000?\001\b\002?\001\001\000�(q|6�\027\017�M\n{\017\007\021&�[��3�4`\000Z??�e�۠�C�`�?�?\006�\035?\\\n3��c?\023�\035\"SA�<3y\"�\\\002?4��L�\007#�#�K������w;>�����\002g��?Q\037?.�?�u??\036)ϼM\b:\037\022R\000ws??\026[i{\000���:?0???!c�n?=J�\024?6LE�C\0242p9��\t`?��\030�V\020?�\"_�\020+?|o\023�]$?��N??g?[g\b'\023?�?W?4W�?�?????:O??#\005\031�\n(64�5?�J��}?Z\n\t?��\006\013\003j'x`?c�\f7��?�\016v�w\002\001\003�?�0?�0\035\006\003U\035\016\004\026\004\024���\022ox\r:��ess??\"k?\02770?�\006\003U\035#\004?�0?�?\024���\022ox\r:��ess??\"k?\0277�??�??0??1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*?H?�\r\001\t\001\026\023android@android.com?\t\000?~O?ֵ??0\f\006\003U\035\023\004\0050\003\001\001�0\r\006\t*?H?�\r\001\001\005\005\000\003?\001\001\000L>�e}&��\021\f?\031�\037?�\t}3?\017i?���F�~?�\017�4{\034uU��<?T\024?F?_?y*\002?��Ga�yG�k���Ƣ����b�w�g\r?o\006.@n�\030\006\f`I?�6?'\021q?�oˡR�\005?�?�Y\037���?+3��\031mwoU�?\032�1?�\f?�?xv\006e\020�I�RJ11��eA�?5B\016��R%Y??Bfi\005rfbO�?��R\027?\035\021\034n\003F\026�Q!\030?��?\023�??�\021�??#?ZT�J�Q�?,Dj?�\024\022\020�DGK@c\007�&?+Ok�U?\034s?Q��`[\005�$?�\025�z?�") };
  static final byte[][] mF = { K("0?\002�0?\002e�\003\002\001\002\002\004P\005|B0\013\006\007*?H�8\004\003\005\000071\0130\t\006\003U\004\006\023\002US1\0200\016\006\003U\004\n\023\007Android1\0260\024\006\003U\004\003\023\rAndroid Debug0\036\027\r120717145250Z\027\r220715145250Z071\0130\t\006\003U\004\006\023\002US1\0200\016\006\003U\004\n\023\007Android1\0260\024\006\003U\004\003\023\rAndroid Debug0?\001�0?\001,\006\007*?H�8\004\0010?\001\037\002??\000?S?\035u\022)R�J?.?���\021�R<�D\000?\036??�Q&iE]@\"Q�Y=?X���?�0��?Ul�?;?\0354o?f`�k?P��??�\004{\020\"�O���?��\033�;W�ƨ�\025\017\004�?���\036?\0025T\023Z\026?2�u�+a�*�?\"\003\031?�H\001�\002\025\000?`P?\025#\013?�?�?��?\013?X\034?\002??\000��?�?=?˼�\\6�W�y?���:�?�WL\013=\007?gQYW?��YO�q\007\020??�I\026q#�L(\026\023��\t2?Ȧ�<\026z?T|?(࣮\036+��u?n�\013�!5b��bz\001$;?��Q??�?��Z�?\006??f^?{U%d\001L;?�I*\003??\000\002??j�\033�?f?z�9�.Ah�?E��?�??{??\034wTi??\rB?���\020??8BO�?�0RN���78c?/�7)??MF��fe�?A\0279\001\003[\034?j�\030\030\r0:�??Y#�jo��uh<E;�\007w|??�ϱ?\02408\024�\035��=[\"+W\006�??0\013\006\007*?H�8\004\003\005\000\003/\0000,\002\024\t?ѰG\002)��??&a�\022?p��\035\002\024gP\002\006�?P�x��\027O\026\004?��") };
  static final byte[][] mG = { K("0?\004L0?\0034�\003\002\001\002\002\t\000��\027�=��?0\r\006\t*?H?�\r\001\001\005\005\0000w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0\036\027\r110324010653Z\027\r380809010653Z0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0?\001 0\r\006\t*?H?�\r\001\001\001\005\000\003?\001\r\0000?\001\b\002?\001\001\000?\017?�ٴ?\tj,XjZ??5k�\002iX��\f]�??I&?�p?�!�>\037[\027\017�bE��?��E'\005;?^4�??K\"??\fR?n&?te�hu�b\037�?@?4[ I\007??Tt:ͪ�eV_H�t�A!��v�5\"��\t\\ �4�j>\\9>�?�/?�b\037??\0375�$?%,o��3?�hk>Ha-\006��oI��\035]?(???\024�WbC???)�?�?\r?&5\023�\005�??� ?~Fu\nZ��?w&/G�?Z<nm{Q4?i��%�\013?\033J??%\013pZ?��>�7?W\001���o�?��\017j[ߵ�G?\002\001\003�?�0?�0\035\006\003U\035\016\004\026\004\024\034��\016�M�\022\037�Q_\r\n\fr�?�m0?�\006\003U\035#\004?�0???\024\034��\016�M�\022\037�Q_\r\n\fr�?�m�{�y0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC?\t\000��\027�=��?0\f\006\003U\035\023\004\0050\003\001\001�0\r\006\t*?H?�\r\001\001\005\005\000\003?\001\001\000�p�(��\033\006ٯj�h�e\004lW?k?CrI1�]L�\f2\025 �<��*�Tb#L?���\020?gk?�??��gcWO�x3\022u�\\�?��\030�??\005\037�����\003����?\004?\037�?|?*I�\"�?�B+�Ui�^��J�\bs��K??t�?���\001$���x\r\030Q+T\n?(��X\031q�\027\r�h�_1�G\022��;�\0207��??�彳^,�k�\"cl\027�j?�zP%?\013?�{1UZ\030E.\0272\032\rR???�?t-t�yXj\\��q?�K�tC\020��'Y\000�=?\006`?\f\"8�\013/�r�ۺu�?."), K("0?\004L0?\0034�\003\002\001\002\002\t\000?v?\004\035vP�0\r\006\t*?H?�\r\001\001\005\005\0000w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0\036\027\r110324010324Z\027\r380809010324Z0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0?\001 0\r\006\t*?H?�\r\001\001\001\005\000\003?\001\r\0000?\001\b\002?\001\001\000��=��*�\rq�\017�@?�6��C��h��v=ǥ*1u|��\037�\020�s�\026�\000\001\004&[4����+��7??�v?(\"����?�\023���5�y?˩�o???x|\021��\031T?\b��r�K??l�\n�'g?�\000u0\026i?�\034?�F���\004?m??1�`(M\022\n��?\035c?\007h}F?Q\023?�?Ƽ? |�\004��\035��{N?uoC`d?�\\�<h�yB�?\026\007�0���e[u?u?�?�\006�9�\013�?\037@B?���Z?Z�?g�???I! B�c��;Tle�`?�?�?�>\037w���?tK\032e\002\001\003�?�0?�0\035\006\003U\035\016\004\026\004\024��?d�]\b?\\4�?\n??\000P\021z?0?�\006\003U\035#\004?�0???\024��?d�]\b?\\4�?\n??\000P\021z?�{�y0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC?\t\000?v?\004\035vP�0\f\006\003U\035\023\004\0050\003\001\001�0\r\006\t*?H?�\r\001\001\005\005\000\003?\001\001\0007q?\f�|<R�???0��b�KM_\022?�]?&\025A??�U]\022?��1,?]�?���L�?�\005�'?\035-?\031?�??x�<?R?\017\030!?\002c�Խ6Q?H�غ&ع?�\t??>�\016��?�7o\036\037�v�\005B?\b\033u*z?�V�D�A�����?�u?�C�s�Pq?W?\f�kz?!�?�?�V�?,\"=\\\007J�U���&?-?d�\n?E��???�?\022~u�S?�0��x�\034?R?\024r�}�\n\r�'J�F44���\024�i}??�?\001�\\}��\005]eV?\004�\0358?��W��p>��J?�4") };
  private static final byte[][] mH = a(new byte[][][] { mD, mE, mF, mG });
  private static final byte[][] mI = { mD[0], mE[0], mG[0] };
  static final byte[][] mJ = { K("0?\002_0?\001Ƞ\003\002\001\002\002\004K\031�?0\r\006\t*?H?�\r\001\001\005\005\0000t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0250\023\006\003U\004\n\023\fGoogle, Inc.1\0200\016\006\003U\004\013\023\007Unknown1\0170\r\006\003U\004\003\023\006Bazaar0\036\027\r091205010429Z\027\r370422010429Z0t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0250\023\006\003U\004\n\023\fGoogle, Inc.1\0200\016\006\003U\004\013\023\007Unknown1\0170\r\006\003U\004\003\023\006Bazaar0??0\r\006\t*?H?�\r\001\001\001\005\000\003??\0000??\002??\000�\b????54w\t?K%\036z)�G7k.\\�[[?\004>??\001\002?\031\024?\023�3��D;\003SJ�J�oP?\022I�?v�?~L?��\033��>ugM��\007Z?sp\001FH��p<-�?4?\005?\022�?l[(�?O?d\021�1�?P\017(\002�l��?o?p���eA?{9\002\003\001\000\0010\r\006\t*?H?�\r\001\001\005\005\000\003??\000I?��AG\001#b:'O��7=u1?\r��j��p[@L�\033�\026?�\030�\021?x?���l�L?\035,�Qj\016��\007N�h??Pd\000�����T\004\022\002?��??��#\020r???J6?��?����\rh<:��?��?2�^6\031�??:?\003j���\031�P") };
  public static boolean mK = false;
  public static boolean mL = false;
  static boolean mM = false;
  private static int mN = -1;
  private static final Object mO = new Object();
  
  private static byte[] K(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("ISO-8859-1");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new AssertionError(paramString);
    }
  }
  
  public static Dialog a(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener, int paramInt3)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity).setMessage(b(paramActivity, paramInt1, paramInt3));
    if (paramOnCancelListener != null) {
      localBuilder.setOnCancelListener(paramOnCancelListener);
    }
    paramOnCancelListener = new du(paramActivity, a(paramActivity, paramInt1, paramInt3), paramInt2);
    paramActivity = b(paramActivity, paramInt1);
    if (paramActivity != null) {
      localBuilder.setPositiveButton(paramActivity, paramOnCancelListener);
    }
    switch (paramInt1)
    {
    default: 
      Log.e("GooglePlayServicesUtil", "Unexpected error code " + paramInt1);
      return localBuilder.create();
    case 0: 
      return null;
    case 4: 
    case 6: 
      return localBuilder.create();
    case 1: 
      return localBuilder.setTitle(R.string.common_google_play_services_install_title).create();
    case 3: 
      return localBuilder.setTitle(R.string.common_google_play_services_enable_title).create();
    case 2: 
      return localBuilder.setTitle(R.string.common_google_play_services_update_title).create();
    case 9: 
      Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
      return localBuilder.setTitle(R.string.common_google_play_services_unsupported_title).create();
    case 7: 
      Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
      return localBuilder.setTitle(R.string.common_google_play_services_network_error_title).create();
    case 8: 
      Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
      return localBuilder.create();
    case 10: 
      Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
      return localBuilder.create();
    case 5: 
      Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
      return localBuilder.setTitle(R.string.common_google_play_services_invalid_account_title).create();
    case 11: 
      Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
      return localBuilder.create();
    }
    Log.e("GooglePlayServicesUtil", "The date of the device is not valid.");
    return localBuilder.setTitle(R.string.common_google_play_services_unsupported_title).create();
  }
  
  public static Intent a(Context paramContext, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      return null;
    case 1: 
    case 2: 
      if (y(paramInt2))
      {
        if (o(paramContext)) {
          return dz.T("com.google.android.gms");
        }
        return dz.S("com.google.android.apps.bazaar");
      }
      return dz.S("com.google.android.gms");
    case 3: 
      return dz.Q("com.google.android.gms");
    }
    return dz.bX();
  }
  
  public static boolean a(Resources paramResources)
  {
    if (paramResources == null) {}
    for (;;)
    {
      return false;
      if ((paramResources.getConfiguration().screenLayout & 0xF) > 3) {}
      for (int i = 1; ((fg.cD()) && (i != 0)) || (b(paramResources)); i = 0) {
        return true;
      }
    }
  }
  
  private static byte[] a(PackageInfo paramPackageInfo, byte[]... paramVarArgs)
  {
    Object localObject;
    try
    {
      localObject = CertificateFactory.getInstance("X509");
      if (paramPackageInfo.signatures.length != 1)
      {
        Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
        return null;
      }
    }
    catch (CertificateException paramPackageInfo)
    {
      Log.w("GooglePlayServicesUtil", "Could not get certificate instance.");
      return null;
    }
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramPackageInfo.signatures[0].toByteArray());
    label139:
    for (;;)
    {
      try
      {
        localObject = (X509Certificate)((CertificateFactory)localObject).generateCertificate(localByteArrayInputStream);
        int i;
        i += 1;
      }
      catch (CertificateException paramPackageInfo)
      {
        try
        {
          ((X509Certificate)localObject).checkValidity();
          paramPackageInfo = paramPackageInfo.signatures[0].toByteArray();
          i = 0;
          if (i >= paramVarArgs.length) {
            break;
          }
          localObject = paramVarArgs[i];
          if (!Arrays.equals((byte[])localObject, paramPackageInfo)) {
            break label139;
          }
          return (byte[])localObject;
        }
        catch (CertificateExpiredException paramPackageInfo)
        {
          Log.w("GooglePlayServicesUtil", "Certificate has expired.");
          return null;
        }
        catch (CertificateNotYetValidException paramPackageInfo)
        {
          Log.w("GooglePlayServicesUtil", "Certificate is not yet valid.");
          return null;
        }
        paramPackageInfo = paramPackageInfo;
        Log.w("GooglePlayServicesUtil", "Could not generate certificate.");
        return null;
      }
    }
    if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
      Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(paramPackageInfo, 0));
    }
    return null;
  }
  
  private static byte[][] a(byte[][]... paramVarArgs)
  {
    int k = paramVarArgs.length;
    int i = 0;
    int j = 0;
    while (i < k)
    {
      j += paramVarArgs[i].length;
      i += 1;
    }
    byte[][] arrayOfByte1 = new byte[j][];
    int m = paramVarArgs.length;
    j = 0;
    i = 0;
    while (j < m)
    {
      byte[][] arrayOfByte2 = paramVarArgs[j];
      k = 0;
      while (k < arrayOfByte2.length)
      {
        arrayOfByte1[i] = arrayOfByte2[k];
        k += 1;
        i += 1;
      }
      j += 1;
    }
    return arrayOfByte1;
  }
  
  public static String b(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      return paramContext.getString(17039370);
    case 1: 
      return paramContext.getString(R.string.common_google_play_services_install_button);
    case 3: 
      return paramContext.getString(R.string.common_google_play_services_enable_button);
    }
    return paramContext.getString(R.string.common_google_play_services_update_button);
  }
  
  public static String b(Context paramContext, int paramInt1, int paramInt2)
  {
    Object localObject = paramContext.getResources();
    switch (paramInt1)
    {
    case 4: 
    case 6: 
    case 8: 
    case 10: 
    case 11: 
    default: 
      paramContext = ((Resources)localObject).getString(R.string.common_google_play_services_unknown_issue);
    case 1: 
    case 3: 
    case 2: 
      do
      {
        return paramContext;
        if (a(paramContext.getResources())) {}
        for (localObject = ((Resources)localObject).getString(R.string.common_google_play_services_install_text_tablet);; localObject = ((Resources)localObject).getString(R.string.common_google_play_services_install_text_phone))
        {
          paramContext = (Context)localObject;
          if (!y(paramInt2)) {
            break;
          }
          return (String)localObject + " (via Bazaar)";
        }
        return ((Resources)localObject).getString(R.string.common_google_play_services_enable_text);
        localObject = ((Resources)localObject).getString(R.string.common_google_play_services_update_text);
        paramContext = (Context)localObject;
      } while (!y(paramInt2));
      return (String)localObject + " (via Bazaar)";
    case 9: 
      return ((Resources)localObject).getString(R.string.common_google_play_services_unsupported_text);
    case 7: 
      return ((Resources)localObject).getString(R.string.common_google_play_services_network_error_text);
    case 5: 
      return ((Resources)localObject).getString(R.string.common_google_play_services_invalid_account_text);
    }
    return ((Resources)localObject).getString(R.string.common_google_play_services_unsupported_date_text);
  }
  
  private static boolean b(Resources paramResources)
  {
    boolean bool2 = false;
    paramResources = paramResources.getConfiguration();
    boolean bool1 = bool2;
    if (fg.cF())
    {
      bool1 = bool2;
      if ((paramResources.screenLayout & 0xF) <= 3)
      {
        bool1 = bool2;
        if (paramResources.smallestScreenWidthDp >= 600) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean bi()
  {
    if (mK) {
      return mL;
    }
    return "user".equals(Build.TYPE);
  }
  
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return a(paramInt1, paramActivity, paramInt2, null, -1);
  }
  
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return a(paramInt1, paramActivity, paramInt2, paramOnCancelListener, -1);
  }
  
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    Intent localIntent = a(paramContext, paramInt1, -1);
    if (localIntent == null) {
      return null;
    }
    return PendingIntent.getActivity(paramContext, paramInt2, localIntent, 268435456);
  }
  
  public static String getErrorString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN_ERROR_CODE";
    case 0: 
      return "SUCCESS";
    case 1: 
      return "SERVICE_MISSING";
    case 2: 
      return "SERVICE_VERSION_UPDATE_REQUIRED";
    case 3: 
      return "SERVICE_DISABLED";
    case 4: 
      return "SIGN_IN_REQUIRED";
    case 5: 
      return "INVALID_ACCOUNT";
    case 6: 
      return "RESOLUTION_REQUIRED";
    case 7: 
      return "NETWORK_ERROR";
    case 8: 
      return "INTERNAL_ERROR";
    case 9: 
      return "SERVICE_INVALID";
    case 10: 
      return "DEVELOPER_ERROR";
    case 11: 
      return "LICENSE_CHECK_FAILED";
    }
    return "DATE_INVALID";
  }
  
  public static String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    Object localObject = new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
    try
    {
      InputStream localInputStream = paramContext.getContentResolver().openInputStream((Uri)localObject);
      try
      {
        paramContext = new Scanner(localInputStream).useDelimiter("\\A").next();
        localObject = paramContext;
        if (localInputStream != null)
        {
          localInputStream.close();
          return paramContext;
        }
      }
      catch (NoSuchElementException paramContext)
      {
        paramContext = paramContext;
        if (localInputStream == null) {
          break label101;
        }
        localInputStream.close();
        break label101;
      }
      finally
      {
        paramContext = finally;
        if (localInputStream != null) {
          localInputStream.close();
        }
        throw paramContext;
      }
      return (String)localObject;
    }
    catch (Exception paramContext)
    {
      localObject = null;
    }
    label101:
    return null;
  }
  
  public static Context getRemoteContext(Context paramContext)
  {
    try
    {
      paramContext = paramContext.createPackageContext("com.google.android.gms", 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static Resources getRemoteResource(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
      if (System.currentTimeMillis() < 1227312000288L) {
        return 12;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
      }
      n(paramContext);
      try
      {
        paramContext = localPackageManager.getPackageInfo("com.android.vending", 64);
        paramContext = a(paramContext, mD);
        if (paramContext == null)
        {
          Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
          return 9;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
        return 9;
      }
      PackageInfo localPackageInfo;
      try
      {
        localPackageInfo = localPackageManager.getPackageInfo("com.google.android.gms", 64);
        if (a(localPackageInfo, new byte[][] { paramContext }) == null)
        {
          Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
          return 9;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
        return 1;
      }
      if (localPackageInfo.versionCode < 4242000)
      {
        Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 4242000 but found " + localPackageInfo.versionCode);
        return 2;
      }
      try
      {
        paramContext = localPackageManager.getApplicationInfo("com.google.android.gms", 0);
        if (!paramContext.enabled) {
          return 3;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
        paramContext.printStackTrace();
        return 1;
      }
    }
    return 0;
  }
  
  public static boolean isUserRecoverableError(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static void m(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    int i = isGooglePlayServicesAvailable(paramContext);
    if (i != 0)
    {
      paramContext = a(paramContext, i, -1);
      Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + i);
      if (paramContext == null) {
        throw new GooglePlayServicesNotAvailableException(i);
      }
      throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", paramContext);
    }
  }
  
  private static void n(Context paramContext)
  {
    Object localObject = null;
    int i;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      paramContext = paramContext.metaData;
      if (paramContext == null) {
        break label115;
      }
      i = paramContext.getInt("com.google.android.gms.version");
      if (i == 4242000) {
        return;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        Log.wtf("GooglePlayServicesUtil", "This should never happen.", paramContext);
        paramContext = (Context)localObject;
      }
    }
    throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 4242000 but found " + i + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
    label115:
    throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
  }
  
  private static boolean o(Context paramContext)
  {
    boolean bool = false;
    if (mK) {
      bool = mM;
    }
    for (;;)
    {
      return bool;
      try
      {
        paramContext = a(paramContext.getPackageManager().getPackageInfo("com.google.android.apps.bazaar", 64), mJ);
        if (paramContext != null) {
          return true;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
    }
    return false;
  }
  
  static boolean y(int paramInt)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    switch (z(paramInt))
    {
    default: 
      bool1 = false;
    case 1: 
    case 0: 
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!bi());
      return false;
    }
    return false;
  }
  
  private static int z(int paramInt)
  {
    int i = paramInt;
    if (paramInt == -1) {
      i = 2;
    }
    return i;
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\common\GooglePlayServicesUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */