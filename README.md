# average-layout
为了满足UI线性布局，均匀留空的特点

继承自LinearLayout

Attribute | Type | Summary
--- | :---: | ---
`holdEdgeSpace` | `boolean` | 是否让layout的左右/上下参与留空的计算
`sizeAccordingId` | `reference` | 根据指定child的大小来计算每个view的size，如果layout_width和layout_height都没有固定值，则无效
`itemWidth` | `dimension` | 每个view的宽度，优先级低于sizeAccordingId
`itemHeight` | `dimension` | 每个view的高度，优先级低于sizeAccordingId
