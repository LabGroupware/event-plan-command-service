# 手動Build手順

``` sh
BUILD_VERSION=1.0.2
./gradlew clean build jibMultiBuild -PimageVersion=$BUILD_VERSION
docker push ablankz/nova-plan-command-service:$BUILD_VERSION-amd64
docker push ablankz/nova-plan-command-service:$BUILD_VERSION-arm64
```