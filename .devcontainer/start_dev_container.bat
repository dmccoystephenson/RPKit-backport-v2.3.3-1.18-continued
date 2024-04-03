docker build . -t rpk-dev-container
docker run -it -v $(pwd)\..\:/workspaces/RPKit rpk-dev-container /bin/bash
