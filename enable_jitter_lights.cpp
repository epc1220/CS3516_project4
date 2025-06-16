#include "LogitechLEDLib.h"

int main() {
    LogiLedInit();
    LogiLedSetTargetDevice(LOGI_DEVICETYPE_ALL);
    LogiLedSaveCurrentLighting();

    // flash red
    LogiLedFlashLighting(100, 0, 0, LOGI_LED_DURATION_INFINITE, 1500);
}