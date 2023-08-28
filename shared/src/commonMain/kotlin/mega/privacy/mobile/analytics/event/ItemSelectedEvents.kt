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