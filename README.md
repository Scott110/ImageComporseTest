# ImageComporseTest
libjeg 压缩图片
借鉴 luban  和 advanceLuan 参考微信的 尺寸和采样压缩，并且通过libJeg 实现jni 压缩
libjpg 目前通过 命令:
git clone git://git.linaro.org/people/tomgall/libjpeg-turbo/libjpeg-turbo.git -b linaro-android
然后调用ndk 目前只实现了 armeabi 和 armeabi-v7a 2中so 文件生成，其他cpu 的格式待后续完善