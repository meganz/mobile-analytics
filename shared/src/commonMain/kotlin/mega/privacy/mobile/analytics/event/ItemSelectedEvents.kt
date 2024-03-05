package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent

@ItemSelectedEvent
class AlbumSelected(val selectionType: SelectionType, imageCount: Int?, videoCount: Int?) {
    enum class SelectionType {
        /**
         * When the user selects an album to open
         */
        Single,

        /**
         * When the user adds to/starts the multi-selection of albums
         */
        MultiAdd,

        /**
         * When the user removes from the multi-selection of albums
         */
        MultiRemove,
    }
}

@ItemSelectedEvent
class AlbumSelectAll(val albumsCount: Int)

@ItemSelectedEvent
class AlbumDeselectAll

@ItemSelectedEvent
class SearchItemSelected(val searchItemType: SearchItemType){
    enum class SearchItemType{
        /**
         * When user selects folder to open
         */
        File,

        /**
         * When user selects file to open
         */
        Folder
    }
}

@ItemSelectedEvent
class SearchImageFilterPressed

@ItemSelectedEvent
class SearchDocsFilterPressed

@ItemSelectedEvent
class SearchAudioFilterPressed

@ItemSelectedEvent
class SearchVideosFilterPressed

@ItemSelectedEvent
class SearchResetFilterPressed

@ItemSelectedEvent
class PhotoItemSelected(val selectionType: SelectionType) {
    enum class SelectionType {
        /**
         * When the user selects an item to open
         */
        Single,

        /**
         * When the user adds to/starts the multi-selection of items
         */
        MultiAdd,

        /**
         * When the user removes from the multi-selection of items
         */
        MultiRemove,
    }
}

@ItemSelectedEvent
class SyncOptionSelected(val selectionType: SelectionType) {
    enum class SelectionType {
        /**
         * When user selects option wifi only
         */
        SyncOptionWifiOnlySelected,
        /**
         * When user selects option SyncOptionWifiAndMobilePressed
         */
        SyncOptionWifiAndMobileSelected
    }
}

@ItemSelectedEvent
class DeviceCenterItemClicked(val itemType: ItemType) {
    enum class ItemType {
        /**
         * When user selects a device to open
         */
        Device,

        /**
         * When user selects a sync/backup/CU connection to open
         */
        Connection
    }
}

@ItemSelectedEvent
class ChatMessageLongPressed