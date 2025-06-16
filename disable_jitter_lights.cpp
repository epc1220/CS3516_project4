#include "LogitechLEDLib.h"

int main() {
    // Restore previously saved lighting when effect is finished
    LogiLedStopEffects();
    LogiLedRestoreLighting();

    LogiLedShutdown();
}