
# 在当前路径下生成 别名为 htgKey 有效时间为365天的 文件名为 htgstore.jks 的证书
# 其中 htgKey 和 htgstore.jks 都可以任意指定
# -alias 产生别名，每个 keystore 都会关联这一个独一无二的 alias，alias 不区分大小写
# -keyalg：指定产生密钥的算法
# -validity：有效天数
# -keystore：密钥库名称
# -keypass：指定别名条目的密码（私钥的密码）
# -storepass：密钥库口令
keytool -genkeypair -alias htgKey -keyalg RSA -validity 365  -keystore htgstore.jks -keypass 123qweasd -storepass qweasdzxc


# 注意 一个 alias 只生成一次，第一次会异常 如下：
##   htg@htg-Latitude-E7240:~/work/key_test$ keytool -genkeypair -alias htgKey -keyalg RSA -validity 365  -keystore htgstore.jks -keypass 123qweasd -storepass qweasdzxc
##   keytool 错误: java.lang.Exception: 未生成密钥对, 别名 <htgKey> 已经存在


#查看秘钥库
keytool -list -v -keystore htgstore.jks -storepass "qweasdzxc"