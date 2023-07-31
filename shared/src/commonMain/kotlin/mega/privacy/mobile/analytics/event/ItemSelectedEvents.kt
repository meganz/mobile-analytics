package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent

@ItemSelectedEvent
class AlbumSelected(val selectionType: SelectionType) {
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
