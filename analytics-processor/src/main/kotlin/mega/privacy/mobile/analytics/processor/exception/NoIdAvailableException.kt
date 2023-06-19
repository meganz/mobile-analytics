package mega.privacy.mobile.analytics.processor.exception

class NoIdAvailableException(name: String) :
    Throwable("Cannot generate a new id for $name as the range has been depleted")