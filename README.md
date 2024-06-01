# RPKit Fork
This is a fork of the popular roleplay framework [RPKit](https://github.com/RP-Kit/RPKit).

## Target Minecraft Version
The target Minecraft version for this fork is 1.18.

## Main Branch
The main branch for this fork of the project is backport/v2.3.3-1.18, which was pulled in from the [parent project](https://github.com/RP-Kit/RPKit/tree/backport/v2.3.3-1.18).

## Set Chat Name Color
The purpose of this fork is to work on the [Customizable Chat Name Colors](https://github.com/RP-Kit/RPKit/issues/658) work item.

## How To Build
1. Install Docker if you don't have it already installed.
1. Reopen project in dev container by running the following:
```
cd .devcontainer
./start_dev_container.sh
```
1. Run `./gradlew clean build` to build the project.

## Testing
Testing can be done using the [rpk-mc-server](https://github.com/dmccoystephenson/rpk-mc-server) project.

## Troubleshooting
### Cannot lock checksums cache (/workspaces/RPKit/.gradle/checksums) as it has already been locked by this process.
This error can occur when trying to build the project. To resolve this issue, run the following:
```
./gradlew --stop
```
This will stop the gradle daemon and allow you to build the project.