package com.zhang.commolib.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.zhang.commolib.base.BaseActivity;

/**
 * Created by ZhangYan on 2018/4/24.
 */

public abstract class TrangramActivity extends BaseActivity {

    private TangramEngine engine;

    @Override
    protected int getResourceID() {
        return 0;
    }

    @Override
    protected void initData() {
        RecyclerView recycleView = getRecycleView();
        if (recycleView == null) {
            throw new RuntimeException("recycleView == null");
        }
        //这一步 builder 对象生成的时候，内部已经注册了框架所支持的所有组件和卡片，以及默认的IAdapterBuilder（它被用来创建 绑定到 RecyclerView 的Adapter）。
        TangramBuilder.InnerBuilder builder = TangramBuilder.newInnerBuilder(this);
        /*
         * 一般情况下，内置卡片的类型已经满足大部分场景了，业务方主要是注册一下自定义组件。注册组件有3种方式：注册绑定组件类型和自定义View，比如builder.registerCell(1, TestView.class);。意思是类型为1的组件渲染时会被绑定到TestView的实例上，
         * 这种方式注册的组件使用通用的组件模型BaseCell。
         * 注册绑定组件类型、自定义 model、自定义View，比如builder.registerCell(1, TestCell.class, TestView.class);。
         * 意思是类型为1的组件使用自定义的组件模型TestCell，它应当继承于BaseCell，在渲染时会被绑定到TestView的实例上。
         * 注册绑定组件类型、自定义model、自定义ViewHolder，
         * 比如builder.registerCell(1, TestCell.class, new ViewHolderCreator<>(R.layout.item_holder, TestViewHolder.class, TestView.class));。
         * 意思是类型为1的组件使用自定义的组件模型TestCell，它应当继承于BaseCell，在渲染时以R.layout.item_holder为布局创建类型为TestView的 view，并绑定到类型为TestViewHolder的 viewHolder 上，组件数据被绑定到定到TestView的实例上。
         */
        builder.registerCell(1, TextView.class);
        //生成TangramEngine实例
        engine = builder.build();
        //Tangram 内部提供了一些常用的 support 类辅助业务开发，业务方也可以自定义所需要的功能模块注册进去。
        // 以下常用三个常用的support，分别处理点击、卡片数据加载、曝光逻辑，详情请参考文档。
        //engine.register(SimpleClickSupport.class, new XXClickSupport());
        //engine.register(CardLoadSupport.class, new XXCardLoadSupport());
//        engine.register(ExposureSupport.class, new XXExposureSuport());
//        绑定 recyclerView
        engine.bindView(recycleView);
        //监听 recyclerView 的滚动事件
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //在 scroll 事件中触发 engine 的 onScroll，内部会触发需要异步加载的卡片去提前加载数据
                engine.onScrolled();
            }
        });
        //设置悬浮类型布局的偏移（可选）
        // 如果你的 recyclerView 上方还覆盖有其他 view，比如底部的 tabbar 或者顶部的 actionbar，为了防止悬浮类 view 和这些外部 view 重叠，可以设置一个偏移量。
//        engine.getLayoutManager().setFixOffset(0, 40, 0, 0);
        //设置卡片预加载的偏移量（可选)
        //在页面滚动过程中触发engine.onScrolled()方法，会去寻找屏幕外需要异步加载数据的卡片，默认往下寻找5个，让数据预加载出来，可以修改这个偏移量。
        engine.setPreLoadNumber(3);
        //设置数据
//        engine.setData(data);
    }

    protected abstract RecyclerView getRecycleView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (engine != null) {
            engine.destroy();
        }
    }
}
