//package mobilize.me.external;
//
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory.Options;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.Gallery;
//import android.widget.ImageView;
//
//public class ImageGallery {
//
//    String[] imageUrls;
//
//    DisplayImageOptions options;
//
//    public ImageGallery() {
//            Options options = new DisplayImageOptions.Builder()
//                    .showImageOnLoading(R.drawable.ic_stub)
//                    .showImageForEmptyUri(R.drawable.ic_empty)
//                    .showImageOnFail(R.drawable.ic_error)
//                    .cacheInMemory(true)
//                    .cacheOnDisc(true)
//                    .bitmapConfig(Bitmap.Config.RGB_565)
//                    .build();
//
//            Gallery gallery = (Gallery) findViewById(R.id.gallery);
//            gallery.setAdapter(new ImageGalleryAdapter());
//            gallery.setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            startImagePagerActivity(position);
//                    }
//            });
//    }
//
//    private void startImagePagerActivity(int position) {
//            Intent intent = new Intent(this, ImagePagerActivity.class);
//            intent.putExtra(Extra.IMAGES, imageUrls);
//            intent.putExtra(Extra.IMAGE_POSITION, position);
//            startActivity(intent);
//    }
//
//    private class ImageGalleryAdapter extends BaseAdapter {
//            @Override
//            public int getCount() {
//                    return imageUrls.length;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                    return position;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                    return position;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                    ImageView imageView = (ImageView) convertView;
//                    if (imageView == null) {
//                            imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_gallery_image, parent, false);
//                    }
//                    imageLoader.displayImage(imageUrls[position], imageView, options);
//                    return imageView;
//            }
//    }
//}