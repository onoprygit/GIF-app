package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "fixed_height")
    val fixedHeight: FixedSize,
    @Json(name = "fixed_width")
    val fixedWidth: FixedSize,



/*    val `480w_still`: WStill,
    val downsized: Downsized,
    val downsized_large: DownsizedLarge,
    val downsized_medium: DownsizedMedium,
    val downsized_small: DownsizedSmall,
    val downsized_still: DownsizedStill,

    val fixed_height_downsampled: FixedHeightDownsampled,
    val fixed_height_small: FixedHeightSmall,
    val fixed_height_small_still: FixedHeightSmallStill,
    val fixed_height_still: FixedHeightStill,

    val fixed_width_downsampled: FixedWidthDownsampled,
    val fixed_width_small: FixedWidthSmall,
    val fixed_width_small_still: FixedWidthSmallStill,
    val fixed_width_still: FixedWidthStill,
    val hd: Hd,
    val looping: Looping,
    val original: Original,
    val original_mp4: OriginalMp4,
    val original_still: OriginalStill,
    val preview: Preview,
    val preview_gif: PreviewGif,
    val preview_webp: PreviewWebp*/
)