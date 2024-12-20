package lsp4custom.com.ninjacoder.customhtmllsp;

import Ninja.coder.Ghostemane.code.ApplicationLoader;
import Ninja.coder.Ghostemane.code.utils.DataUtil;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.github.rosemoe.sora.data.CompletionItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaCardshorts extends Css3Server {

  private List<CompletionItem> item;

  private String prf;

  public JavaCardshorts(List<CompletionItem> item, String prf) {
    this.item = item;
    this.prf = prf;
    try {
      asChild();
    } catch (Exception e) {
    }
  }

  void asChild() throws IOException {
    boolean isLen = prf.length() > 0;
    List<Map<String, String>> listItem = new ArrayList<>();
    var inputStream = ApplicationLoader.getContext().getAssets().open("javasnippet.json");
    listItem =
        new Gson()
            .fromJson(
                DataUtil.copyFromInputStream(inputStream),
                new TypeToken<List<Map<String, String>>>() {}.getType());
    listItem.forEach(
        it -> {
          if (it.get("name").startsWith(prf) && isLen) {
            item.add(css(it.get("name"), "Snippet", it.get("snippet")));
          }
        });
  }
}
