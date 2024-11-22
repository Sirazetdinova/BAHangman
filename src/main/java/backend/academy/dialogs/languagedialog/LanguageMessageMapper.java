package backend.academy.dialogs.languagedialog;

import backend.academy.dialogs.common.messagemapper.AbstractOptionMessageMapper;

public class LanguageMessageMapper extends AbstractOptionMessageMapper {
    private static final String INVALID_LANGUAGE = "Invalid language";

    public LanguageMessageMapper() {
        super((section, key) -> INVALID_LANGUAGE);
    }

    @Override
    protected String messageInputDoesNotMatchWithOptions() {
        return INVALID_LANGUAGE;
    }
}
