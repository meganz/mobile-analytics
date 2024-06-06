package mega.privacy.mobile.analytics.core

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.sizeOf
import platform.windows.OSVERSIONINFOEXW

class WindowsPlatform : Platform {
    /**
     * Name
     */
    override val name: String = getWindowsVersion()

    /**
     * Base identifier
     */
    override val baseIdentifier = 700_000
}

/**
 * Get platform
 *
 * @return Windows Platform
 */
actual fun getPlatform(): Platform = WindowsPlatform()

@OptIn(ExperimentalForeignApi::class)
private fun getWindowsVersion(): String {
    memScoped {
        val osVersionInfo = alloc<OSVERSIONINFOEXW>()
        osVersionInfo.dwOSVersionInfoSize = sizeOf<OSVERSIONINFOEXW>().toUInt()
        return "Windows ${osVersionInfo.dwMajorVersion}.${osVersionInfo.dwMinorVersion} (Build ${osVersionInfo.dwBuildNumber})"
    }
}